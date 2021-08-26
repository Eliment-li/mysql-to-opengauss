package com.tiange.core.migrate.insert;

/**
 * 工人线程
 * 生产者-消费者模式中的 消费者角色
 */
public class InsertHub extends Thread {

    private final InsertChannel insertChannel;

    public InsertHub(String name, InsertChannel insertChannel) {
        super(name);
        this.insertChannel = insertChannel;
    }

    public void run() {
        try {
            while (true) {
                InsertRequest insertRequest = insertChannel.takeRequest();
                insertRequest.execute();
            }
        } catch (Exception e) {//interrupted exception
            e.printStackTrace();
        }
    }

}
