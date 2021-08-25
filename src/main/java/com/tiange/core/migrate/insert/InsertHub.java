package com.tiange.core.migrate.insert;

/**
 * 工人线程
 * 生产者-消费者模式中的 消费者角色
 */
public class InsertHub extends Thread {

    private final InsertChannel channel;

    public InsertHub(String name, InsertChannel channel) {
        super(name);
        this.channel = channel;
    }

    public void run() {
        try {
            while (true) {
                InsertRequest insertRequest = channel.take();
                insertRequest.execute();
            }
        } catch (Exception e) {//interrupted exception
            e.printStackTrace();
        }
    }

}
