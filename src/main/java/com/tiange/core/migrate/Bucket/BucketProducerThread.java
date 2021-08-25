package com.tiange.core.migrate.Bucket;

import com.tiange.core.mysql.database.MysqlDatabase;

import java.util.concurrent.Exchanger;

public class BucketProducerThread extends Thread {
    /*
     *当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，直到线程B也调用了exchange()方法，然后以线程安全的方式交换数据，之后线程A和B继续运行。
     */
    private final Exchanger<Bucket> exchanger;
    private Bucket bucket = null;
    private MysqlDatabase mysqlDatabase;


    public BucketProducerThread(Exchanger<Bucket> exchanger, MysqlDatabase mysqlDatabase) {
        super("BucketProducerThread");
        this.exchanger = exchanger;
        this.mysqlDatabase = mysqlDatabase;
    }

    public void run() {
        try {
            //获取bucket
            bucket = getNextBucket();
            //交换缓冲区
            bucket = exchanger.exchange(bucket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取下一个数据块,如果没有下一个数据块，返回 null
     *
     * @return 数据块
     */
    private Bucket getNextBucket() {
        Bucket bucket = new Bucket();

        return bucket;
    }
}
