package com.tiange.core.migrate.insert;

import com.tiange.core.utils.database.jdbc.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class InsertRequest {

    private final Page page;
    //日志工具
    private Logger logger = LoggerFactory.getLogger(InsertRequest.class);

    public InsertRequest(Page page) {
        this.page = page;
    }

    public void execute() {
        try {
            //插入数据
            insert2OpenGauss();

            //将数据放入校验队列中
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //将数据插入到OpenGauss数据库中，
    private void insert2OpenGauss() {
        //要插入的数据
        List<Map<String, Object>> Data = page.getPageContent();
        logger.info("插入数据{}-{}", Data.get(0).get("id"), Data.get(Data.size() - 1).get("id"));

    }
}
