package com.tiange.core.migrate;

/**
 * 工人线程
 * 生产者-消费者模式中的 消费者角色
 */
public class PageWorker extends Thread {

    private final PageChannel channel;

    public PageWorker(String name, PageChannel channel) {
        super(name);
        this.channel = channel;
    }

    public void run() {
        try {
            while (true) {
                Request request = channel.take();
                request.execute();
            }
        } catch (Exception e) {//interrupted exception
            e.printStackTrace();
        }
    }

}
