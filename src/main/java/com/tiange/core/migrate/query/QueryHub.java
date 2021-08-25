package com.tiange.core.migrate.query;

public class QueryHub extends Thread {
    //查询请求缓冲区
    private final QueryChannel channel;

    public QueryHub(String name, QueryChannel channel) {
        super(name);
        this.channel = channel;
    }

    public void run() {
        try {
            QueryRequest queryRequest = channel.take();
            queryRequest.execute();

        } catch (Exception e) {//interruptedexception
            e.printStackTrace();
        }
    }
}
