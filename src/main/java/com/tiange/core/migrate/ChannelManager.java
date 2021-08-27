package com.tiange.core.migrate;

import com.tiange.core.migrate.insert.InsertChannel;
import com.tiange.core.migrate.query.QueryChannel;
import com.tiange.core.migrate.verify.VerifyChannel;

public class ChannelManager {

    //保存查询请求的缓存
    final private QueryChannel queryChannel;
    //保存插入请求的缓存
    final private InsertChannel insertChannel;
    //保存验证请求的缓存
    final private VerifyChannel verifyChannel;

    public ChannelManager(int queryThreads, int insertThreads, int verifyThreads) {
        this.queryChannel = new QueryChannel(queryThreads);
        this.insertChannel = new InsertChannel(insertThreads);
        this.verifyChannel = new VerifyChannel(verifyThreads);
    }

    //启动各自的工作线程
    public void startWorkers() {
        queryChannel.startWorkers();
        insertChannel.startWorkers();
        //verifyChannel.startWorkers();
    }

    public QueryChannel getQueryChannel() {
        return this.queryChannel;
    }

    public InsertChannel getInsertChannel() {
        return this.insertChannel;
    }

    public VerifyChannel getVerifyChannel() {
        return this.verifyChannel;
    }
}
