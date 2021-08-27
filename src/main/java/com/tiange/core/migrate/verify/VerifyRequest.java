package com.tiange.core.migrate.verify;

import com.tiange.core.migrate.query.QueryRequest;
import com.tiange.core.utils.database.jdbc.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerifyRequest {

    Page page;

    //数据插入工作请求缓存
    // private final v insertChannel;

    //日志工具
    private Logger logger = LoggerFactory.getLogger(QueryRequest.class);

    public VerifyRequest(Page pagel) {

        this.page = page;
    }

    public void execute() throws InterruptedException {


    }
}
