package com.tiange.core.migrate.Bucket;

import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.mysql.table.MysqlTable;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;

public class BucketProducerThread extends Thread {
    /*
     *当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，直到线程B也调用了exchange()方法，然后以线程安全的方式交换数据，之后线程A和B继续运行。
     */
    private final Exchanger<Bucket> exchanger;
    private Bucket bucket;
    private MysqlDatabase mysqlDatabase;


    public BucketProducerThread(Exchanger<Bucket> exchanger, Bucket bucket, MysqlDatabase mysqlDatabase) {
        super("BucketProducerThread");
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
                    this.bucket = new Bucket(table.getTable_name(), 1000);
                    this.bucket.setNumber(bucketNumber);

                    //判断
                    if (hasBucket(bucket)) {
                        //交换缓冲区
                        bucket = exchanger.exchange(bucket);
                        bucketNumber++;

                    } else {
                        bucket = exchanger.exchange(null);
                        break;
                    }
                }//end while
            }

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
                Bucket bucket = new Bucket(table.getTable_name(), 1000);
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

    private boolean hasBucket(Bucket bucket) throws SQLException {

        String tableName = bucket.getTableName();
        long bucketSize = bucket.getSize();
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

        //todo 此处可优化
        List<Map<String, Object>> result = MySqlDbUtil.queryForMapList(sql.toString());

        if (result.size() > 0)
            return true;
        else
            return false;
    }
}
