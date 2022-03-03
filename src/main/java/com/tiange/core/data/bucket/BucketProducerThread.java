package com.tiange.core.data.bucket;

import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.mysql.table.MysqlTable;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;



public class BucketProducerThread extends Thread {
    /*
     * 当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，
     * 直到线程B也调用了exchange()方法，然后以线程安全的方式交换数据，
     * 之后线程A和B继续运行。
     */
    private final Exchanger<Bucket> exchanger;
    private Bucket bucket;
    private MysqlDatabase mysqlDatabase;


    public BucketProducerThread(Exchanger<Bucket> exchanger, Bucket bucket, MysqlDatabase mysqlDatabase) {
        super("BucketProducer");
        this.exchanger = exchanger;
        this.bucket = bucket;
        this.mysqlDatabase = mysqlDatabase;
    }

    public void run() {
        try {
            List<MysqlTable> tables = mysqlDatabase.getMysqlTables();

            for (int i = 0; i < tables.size(); i++) {

                MysqlTable table = tables.get(i);

                long bucketNumber = 0;

                while (true) {
                    //查询
                    this.bucket = new Bucket(table);
                    this.bucket.setNumber(bucketNumber);

                    //判断
                    if (hasBucket(bucket)) {
                        //交换缓冲区
                        bucket = exchanger.exchange(bucket);
                        bucketNumber++;

                    } else {
                        break;
                    }
                }//end while
            }

            //往交换区放置null, 暗示所有数据读取完毕，消费者线程可以结束。
            exchanger.exchange(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取下一个数据块,如果没有下一个数据块，返回 null
     *
     * @return 数据块
     */
    private Bucket getNextBucket() throws SQLException {

        List<MysqlTable> tables = mysqlDatabase.getMysqlTables();

        // MySqlDbUtil.queryForMapList();

        for (int i = 0; i < tables.size(); i++) {
            MysqlTable table = tables.get(i);
            long bucketNumber = 0;
            while (true) {
                //查询
                Bucket bucket = new Bucket(table);
                bucket.setNumber(bucketNumber);
                //判断
                if (hasBucket(bucket)) {
                    return bucket;
                } else {
                    break;
                }
            }

        }

        return bucket;
    }

    /**
     * 该 bucket 是否还存在，由于bucket 是顺序读取，
     * 如果该 bucket 不存在，说明表中数据已经读取完毕
     *
     * 注：该函数功能 与 getNextBucket()方法存在重合，
     * 应当将这两个方法优化为同一个方法。
     *
     * @param bucket 数据块
     *
     * @return 是否存在
     */
    private boolean hasBucket(Bucket bucket) {

        //要查询的数据库表名
        String tableName = bucket.mysqlTable.getTable_name();
        long bucketSize = bucket.getSize();

        //拼接 sql
        StringBuilder sql = new StringBuilder("select * from " + tableName + "  ");

        if (bucket.getNumber() <= 1) {

            sql.append(" limit " + (bucketSize));

            bucket.setStart(0);
            bucket.setEnd(bucketSize);

        } else {
            long offset = (bucket.getNumber() - 1) * bucketSize;
            sql.append(" limit " + offset + "," + (bucketSize));

            bucket.setStart(offset);
            bucket.setEnd(offset + bucketSize);
        }

        //执行查询
        List<Map<String, Object>> result = MySqlDbUtil.queryForMapList(sql.toString());

        return result.size() > 0;
    }
}
