package com.tiange.core.migrate;

import com.tiange.core.migrate.insert.InsertChannel;
import com.tiange.core.migrate.query.QueryChannel;
import com.tiange.core.migrate.verify.VerifyChannel;
import com.tiange.core.utils.others.FileUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 缓冲区容器，管理所有的缓冲区
 */
public class ChannelManager {

    //保存查 询请求 的缓存
    final private QueryChannel queryChannel;
    //保存 插入请求 的缓存
    final private InsertChannel insertChannel;
    //保存 验证请求 的缓存
    final private VerifyChannel verifyChannel;

    public ChannelManager() {

        //根据配置文件初始化缓存队列
        Properties properties = new Properties();
        try {
            properties.load(FileUtils.getInputStreamByClasspath("config/data_migrate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.queryChannel = new QueryChannel(Integer.parseInt(properties.getProperty("queryThreads")));
        this.insertChannel = new InsertChannel(Integer.parseInt(properties.getProperty("insertThreads")));
        this.verifyChannel = new VerifyChannel(Integer.parseInt(properties.getProperty("verifyThreads")));
    }

    public ChannelManager(int queryThreads, int insertThreads, int verifyThreads) {
        this.queryChannel = new QueryChannel(queryThreads);
        this.insertChannel = new InsertChannel(insertThreads);
        this.verifyChannel = new VerifyChannel(verifyThreads);
    }

    //启动各自的工作线程
    public void startWorkers() {
        queryChannel.startWorkers();
        insertChannel.startWorkers();
        verifyChannel.startWorkers();
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
