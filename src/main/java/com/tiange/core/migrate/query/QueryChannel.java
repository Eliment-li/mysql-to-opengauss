package com.tiange.core.migrate.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;

public class QueryChannel extends ArrayBlockingQueue<QueryRequest> {

    private final QueryHub[] threadPool;
    //日志工具
    private Logger logger = LoggerFactory.getLogger(QueryChannel.class);
    public QueryChannel(int threads) {
        //缓冲区容量
        super(100);
        //线程池大小
        threadPool = new QueryHub[threads];

        // InsertChannel insertChannel = new InsertChannel(1);

        for (int i = 0; i < threads; i++) {
            threadPool[i] = new QueryHub("pageClient" + i, this);
        }
    }

    //启动工作线程
    public void startWorkers() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    public void putRequest(QueryRequest request) throws InterruptedException {
        super.put(request);
    }

    public QueryRequest takeRequest() throws InterruptedException {
        QueryRequest request = super.take();
        return request;
    }
}
