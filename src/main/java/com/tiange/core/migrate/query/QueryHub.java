package com.tiange.core.migrate.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryHub extends Thread {
    //查询请求缓冲区
    private final QueryChannel queryChannel;//final

    //日志工具
    private final Logger logger = LoggerFactory.getLogger(QueryHub.class);

    public QueryHub(String name, QueryChannel queryChannel) {
        super(name);
        this.queryChannel = queryChannel;
    }

    public void run() {
        try {
            while (true) {

                QueryRequest queryRequest = queryChannel.takeRequest();
                queryRequest.execute();
                //Thread.sleep(3000);
            }

        } catch (Exception e) {//interruptedexception
            e.printStackTrace();
        }
    }
}
