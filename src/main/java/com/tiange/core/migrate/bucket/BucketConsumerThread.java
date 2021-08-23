package com.tiange.core.migrate.bucket;

import com.tiange.core.migrate.PageClient;

import java.util.concurrent.Exchanger;

public class BucketConsumerThread extends Thread {
    private final Exchanger<Bucket> exchanger;
    private final PageClient[] pageClientThreadPool;
    private Bucket bucket = null;

    public BucketConsumerThread(Exchanger<Bucket> exchanger) {
        this.exchanger = exchanger;
        pageClientThreadPool = new PageClient[3];
    }

    public void run() {
        try {
            //pageclient.start();
            while (true) {
                bucket = exchanger.exchange(bucket);

                //将bucket分成若干个page 放入channel

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
