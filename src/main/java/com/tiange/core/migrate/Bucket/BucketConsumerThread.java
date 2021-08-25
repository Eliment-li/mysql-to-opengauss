package com.tiange.core.migrate.Bucket;

import com.tiange.core.migrate.insert.InsertChannel;
import com.tiange.core.migrate.query.QueryChannel;
import com.tiange.core.migrate.query.QueryRequest;

import java.util.concurrent.Exchanger;

public class BucketConsumerThread extends Thread {
    private final Exchanger<Bucket> exchanger;


    //查询请求的缓存
    private QueryChannel queryChannel;
    private Bucket bucket = null;

    //插入请求的缓存
    private InsertChannel insertChannel = new InsertChannel(5);

    public BucketConsumerThread(Exchanger<Bucket> exchanger, QueryChannel queryChannel) {
        this.exchanger = exchanger;
        this.queryChannel = queryChannel;
    }


    public void run() {
        //启动工人线程
        queryChannel.startWorkes();
        insertChannel.startWorkers();
        //verifyChannel.startWorkers();

        try {
            while (true) {
                bucket = exchanger.exchange(bucket);

                //将bucket分成若干个page 放入channel

                QueryRequest queryRequest = new QueryRequest("", 0, 0, 0, insertChannel);

                queryChannel.put(queryRequest);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
