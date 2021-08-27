package com.tiange.core.migrate.query;

import com.tiange.core.migrate.ChannelManager;
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

    private final InsertChannel insertChannel;
    private final ChannelManager channelManager;


    //日志工具
    private Logger logger = LoggerFactory.getLogger(QueryRequest.class);

    public QueryRequest(Page page, ChannelManager channelManager) {

        this.page = page;
        this.insertChannel = channelManager.getInsertChannel();
        this.channelManager = channelManager;
    }

    public void execute() throws InterruptedException {
        logger.info("执行查询工作");

        //查询配置数据，放入 pageContent 中
        MySqlDbUtil.queryForPage("select * from " + page.getMysqlTable().getTable_name(), this.page);

        List<Map<String, Object>> content = page.getPageContent();

        if (content.size() == 0) {
            logger.info("传输完毕");
        } else {
            logger.info("查询 page{} {}-{}", page.getPageNum(), content.get(0).get("id"), content.get(content.size() - 1).get("id"));
        }

        InsertRequest insertRequest = new InsertRequest(page, channelManager);
        insertChannel.putRequest(insertRequest);


    }

}
