package com.tiange.core.migrate.query;

public class QueryHub extends Thread {
    //查询请求缓冲区
    private final QueryChannel queryChannel;//final


    public QueryHub(String name, QueryChannel queryChannel) {
        super(name);
        this.queryChannel = queryChannel;
    }

    public void run() {
        try {
            QueryRequest queryRequest = queryChannel.take();
            queryRequest.execute();

        } catch (Exception e) {//interruptedexception
            e.printStackTrace();
        }
    }
}
