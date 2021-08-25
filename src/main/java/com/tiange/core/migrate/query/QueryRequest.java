package com.tiange.core.migrate.query;

import com.tiange.core.migrate.Bucket.Bucket;
import com.tiange.core.migrate.insert.InsertChannel;
import com.tiange.core.migrate.insert.InsertRequest;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.database.jdbc.Page;

public class QueryRequest {

    Bucket bucket;
    Page page;

    private InsertChannel insertChannel = null;

    public QueryRequest(Bucket bucket, Page page, InsertChannel insertChannel) {
        this.bucket = bucket;
        this.page = page;
        this.insertChannel = insertChannel;
    }

    public void execute() throws InterruptedException {
        // System.out.println("执行查询工作，并将插入工作，放入缓冲中");

        Page page = new Page();
        //查询配置数据，放入 pageContent 中
        MySqlDbUtil.queryForPage("select * from " + bucket.getTableName(), page);

        //todo 将page 赋给 InsertRequest

        InsertRequest insertRequest = new InsertRequest();

        insertChannel.put(insertRequest);


    }

}
