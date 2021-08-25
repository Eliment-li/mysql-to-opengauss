package com.tiange.core.migrate.query;

import java.util.concurrent.ArrayBlockingQueue;

public class QueryChannel extends ArrayBlockingQueue<QueryRequest> {

    private final QueryHub[] queryHubThreadPool;

    public QueryChannel(int count, int threads) {
        super(count);
        queryHubThreadPool = new QueryHub[threads];

       /* InsertChannel insertChannel = new InsertChannel(3);
        for (int i = 0; i < threads; i++) {
            queryHubThreadPool[i] = new QueryHub("pageClient" + i, insertChannel);
        }*/
    }

    //启动工作线程
    public void startWorkes() {
        for (int i = 0; i < queryHubThreadPool.length; i++) {
            queryHubThreadPool[i].start();
        }
    }

    public synchronized void putRequest(QueryRequest request) throws InterruptedException {
        super.put(request);
    }

    public synchronized QueryRequest getRequest() throws InterruptedException {
        QueryRequest request = super.take();
        return request;
    }
}
