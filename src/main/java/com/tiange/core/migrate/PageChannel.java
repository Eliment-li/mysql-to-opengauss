package com.tiange.core.migrate;

import java.util.concurrent.ArrayBlockingQueue;


/**
 * 生产者-消费者模式中的 Channel 角色
 * 负责传递工作请求，以及保存工程线程
 */
public class PageChannel extends ArrayBlockingQueue<Request> {

    private static final int MAX_REQUEST = 100;

    /*请求队列*/
    private final Request[] requestQueue = new Request[MAX_REQUEST];

    private final PageWorker[] threadPool;

    public PageChannel(int threads) {
        super(threads);

        threadPool = new PageWorker[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new PageWorker("Worker-" + i, this);
        }
    }

    public void put(Request request) throws InterruptedException {
        super.put(request);
    }

    public Request take() throws InterruptedException {
        Request request = super.take();
        return request;
    }
}
