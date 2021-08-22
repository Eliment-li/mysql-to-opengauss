package com.tiange.core.migrate;

public class PageClient extends Thread {
    private final PageChannel channel;


    public PageClient(String name, PageChannel channel) {
        super(name);
        this.channel = channel;
    }

    public void run() {
        try {
            //todo 获取page，放置到 请求队列中
        } catch (Exception e) {//interruptedexception
            e.printStackTrace();
        }
    }
}
