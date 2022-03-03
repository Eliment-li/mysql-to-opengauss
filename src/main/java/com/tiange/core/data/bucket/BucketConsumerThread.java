package com.tiange.core.data.bucket;

import com.tiange.core.data.cache.ChannelManager;
import com.tiange.core.data.query.QueryChannel;
import com.tiange.core.data.query.QueryRequest;
import com.tiange.core.utils.database.jdbc.Page;
import com.tiange.core.utils.others.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;

public class BucketConsumerThread extends Thread {
    private final Exchanger<Bucket> exchanger;

    private final ChannelManager channelManager;

    //日志工具
    private final Logger logger = LoggerFactory.getLogger(BucketConsumerThread.class);

    private Bucket bucket;

    public BucketConsumerThread(Exchanger<Bucket> exchanger, Bucket bucket, ChannelManager channelManager) {

        super("BucketConsumer");
        this.exchanger = exchanger;
        this.bucket = bucket;
        this.channelManager = channelManager;

    }


    public void run() {

        //TODO  刚开始总是会卡顿一会儿

        channelManager.startWorkers();

        boolean test = true;
        try {
            while (true) {

                //交换缓冲区
                bucket = exchanger.exchange(bucket);

                if (bucket == null) {
                    //bucket读取完毕
                    break;
                }

                logger.info("获取bucket{}", bucket.getNumber());

                //将 bucket 分成若干个 page 放入 channel
                int pageSize = Integer.parseInt(SystemProperties.dataMigrate("pageSize"));

                //一个 bucket 含 count 个 page
                long count = bucket.getSize() / pageSize;

                //pageNumber 的偏移量
                long numberOffset = bucket.getNumber() * count;

                for (int i = 1; i <= count; i++) {

                    Page page = new Page();
                    page.setPageSize(pageSize);
                    page.setPageNum(numberOffset + i);
                    page.setMysqlTable(bucket.getMysqlTable());

                    QueryRequest queryRequest = new QueryRequest(page, channelManager);

                    QueryChannel queryChannel = channelManager.getQueryChannel();
                    queryChannel.putRequest(queryRequest);

                    logger.info("查询请求：{}  {}:page{}", page.getMysqlTable().getTable_name(), bucket.getNumber(), page.getPageNum());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
