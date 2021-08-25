package com.tiange.core.migrate.insert;

import java.util.concurrent.ArrayBlockingQueue;


/**
 * 生产者-消费者模式中的 Channel 角色
 * 负责传递工作请求，以及保存工程线程
 */
public class InsertChannel extends ArrayBlockingQueue<InsertRequest> {

    private static final int MAX_REQUEST = 100;

    private final InsertHub[] threadPool;

    public InsertChannel(int threads) {

        super(100);

        threadPool = new InsertHub[threads];

        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new InsertHub("Worker-" + i, this);
        }

    }

    // 启动 工人线程
    public void startWorkers() {

        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }

    }

    public synchronized void put(InsertRequest insertRequest) throws InterruptedException {
        super.put(insertRequest);
    }

    public synchronized InsertRequest take() throws InterruptedException {
        InsertRequest insertRequest = super.take();
        return insertRequest;
    }
}
