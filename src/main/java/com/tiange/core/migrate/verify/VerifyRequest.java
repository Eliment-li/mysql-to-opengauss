package com.tiange.core.migrate.verify;

import com.tiange.core.migrate.query.QueryRequest;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;
import com.tiange.core.utils.database.jdbc.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class VerifyRequest {

    Page page;

    //数据插入工作请求缓存
    // private final v insertChannel;

    //日志工具
    private Logger logger = LoggerFactory.getLogger(QueryRequest.class);

    public VerifyRequest(Page page) {

        this.page = page;
    }

    public void execute() throws InterruptedException {

        List<Map<String, Object>> mysqlData = page.getPageContent();

        if (page.getPageContent().size() == 0) {
            logger.info("结束");
            return;
        }

        // 查询opengauss的数据
        Page newPage = new Page();
        newPage.setPageNum(page.getPageNum());
        newPage.setPageSize(page.getPageSize());
        newPage.setMysqlTable(page.getMysqlTable());


        OpenGaussDbUtil.queryForPage(newPage);
        List<Map<String, Object>> gaussData = newPage.getPageContent();
        logger.info("验证page{}", page.getPageNum());

        //比较
        if (!verifyPageData(mysqlData, gaussData)) {
            logger.error("数据传输有误：table：{}-page{}", page.getMysqlTable().getTable_name(), page.getPageNum());
        }

    }

    /**
     * 比较两个page的数据是否相等
     *
     * @param mysqlData
     * @param gaussData
     * @return
     */
    private boolean verifyPageData(List<Map<String, Object>> mysqlData, List<Map<String, Object>> gaussData) {

        if (mysqlData.size() != gaussData.size()) return false;

        for (int i = 0; i < mysqlData.size(); i++) {

            //若存在不相等的，直接返回false
            if (!areEqual(mysqlData.get(i), gaussData.get(i))) {
                logger.info("");
                return false;
            }

        }

        return true;
    }


    /**
     * 比较两个 Map 是否相等，比较内容：key 和 value
     *
     * @param first
     * @param second
     * @return
     */
    private boolean areEqual(Map<String, Object> first, Map<String, Object> second) {
        if (first.size() != second.size()) {
            return false;
        }

        return first.entrySet().stream()
                .allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }


}
