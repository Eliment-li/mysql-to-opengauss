package com.tiange.core.migrate.query;

import com.tiange.core.migrate.insert.InsertChannel;
import com.tiange.core.migrate.insert.InsertRequest;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.database.jdbc.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class QueryRequest {

    Page page;

    //数据插入工作请求缓存
    private final InsertChannel insertChannel;

    //日志工具
    private Logger logger = LoggerFactory.getLogger(QueryRequest.class);

    public QueryRequest(Page page, InsertChannel insertChannel) {

        this.page = page;
        this.insertChannel = insertChannel;
    }

    public void execute() throws InterruptedException {
        logger.info("执行查询工作");

        //查询配置数据，放入 pageContent 中
        MySqlDbUtil.queryForPage("select * from " + page.getMysqlTable().getTable_name(), this.page);

        List<Map<String, Object>> content = page.getPageContent();

        logger.info("查询 page{} {}-{}", page.getPageNum(), content.get(0).get("id"), content.get(content.size() - 1).get("id"));

        Thread.sleep(1000);


        InsertRequest insertRequest = new InsertRequest(page);
        logger.info(" 插入请求 开始");
        logger.info(insertChannel.remainingCapacity() + "");
        insertChannel.putRequest(insertRequest);
        logger.info(" 插入请求 结束");


    }

}
