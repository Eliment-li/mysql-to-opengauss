package com.tiange.core.data.insert;

import com.tiange.core.data.cache.ChannelManager;
import com.tiange.core.data.verify.VerifyChannel;
import com.tiange.core.data.verify.VerifyRequest;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;
import com.tiange.core.utils.database.jdbc.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class InsertRequest {

    private final Page page;
    private final ChannelManager channelManager;
    private final VerifyChannel verifyChannel;
    //日志工具
    private Logger logger = LoggerFactory.getLogger(InsertRequest.class);

    public InsertRequest(Page page, ChannelManager channelManager) {
        this.page = page;
        this.verifyChannel = channelManager.getVerifyChannel();
        this.channelManager = channelManager;
    }

    public void execute() {
        try {
            //插入数据
            insert2OpenGauss();

            //将数据放入校验队列中
            VerifyRequest verifyRequest = new VerifyRequest(page);
            verifyChannel.putRequest(verifyRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //将数据插入到OpenGauss数据库中，
    private void insert2OpenGauss() {

        //要插入的数据
        List<Map<String, Object>> Data = page.getPageContent();

        if (page.getPageContent().size() == 0) {
            logger.info("结束{}", page.getPageNum());
            return;
        }
        OpenGaussDbUtil.InsertAll(page.getMysqlTable().getTable_name(), Data);
        logger.info("插入数据{}-{}-{}", page.getPageNum(), Data.get(0).get("id"), Data.get(Data.size() - 1).get("id"));

    }
}
