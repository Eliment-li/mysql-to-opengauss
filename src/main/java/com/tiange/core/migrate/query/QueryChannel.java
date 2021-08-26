package com.tiange.core.migrate.query;

import com.tiange.core.migrate.insert.InsertChannel;

import java.util.concurrent.ArrayBlockingQueue;

public class QueryChannel extends ArrayBlockingQueue<QueryRequest> {

    private final QueryHub[] queryHubThreadPool;

    public QueryChannel(int threads) {
        //缓冲区容量
        super(1);
        queryHubThreadPool = new QueryHub[threads];

        InsertChannel insertChannel = new InsertChannel(1);
        for (int i = 0; i < threads; i++) {
            queryHubThreadPool[i] = new QueryHub("pageClient" + i, this);
        }
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
