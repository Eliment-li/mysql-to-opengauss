package com.tiange.core.migrate.query;

import com.tiange.core.migrate.insert.InsertChannel;
import com.tiange.core.migrate.insert.InsertRequest;

public class QueryRequest {
    private String TableName;
    private long BucketNumber;
    private long pageNumber;
    private long pageSize;

    private InsertChannel insertChannel = null;

    public QueryRequest(String tableName, long bucketNumber, long pageNumber, long pageSize, InsertChannel insertChannel) {
        TableName = tableName;
        BucketNumber = bucketNumber;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.insertChannel = insertChannel;
    }

    public void execute() throws InterruptedException {
        System.out.println("执行查询工作，并将插入工作，放入缓冲中");

        InsertRequest insertRequest = new InsertRequest();
        insertChannel.put(insertRequest);
        //查询配置数据，放入 pageContent 中

        //将查询好的page，放入pageChannel
    }

}
