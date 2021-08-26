package com.tiange.core.migrate.Bucket;

import com.tiange.core.migrate.insert.InsertChannel;
import com.tiange.core.migrate.query.QueryChannel;
import com.tiange.core.migrate.query.QueryRequest;
import com.tiange.core.utils.database.jdbc.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;

public class BucketConsumerThread extends Thread {
    private final Exchanger<Bucket> exchanger;

    //查询请求的缓存
    final private QueryChannel queryChannel;
    //插入请求的缓存
    final private InsertChannel insertChannel;

    //日志工具
    private final Logger logger = LoggerFactory.getLogger(BucketConsumerThread.class);

    private Bucket bucket;

    public BucketConsumerThread(Exchanger<Bucket> exchanger, Bucket bucket) {

        super("BucketConsumer");
        this.exchanger = exchanger;
        this.bucket = bucket;

        queryChannel = new QueryChannel(1);
        insertChannel = new InsertChannel(1);

    }


    public void run() {
        //启动工人线程
        queryChannel.startWorkes();
        insertChannel.startWorkers();
        //verifyChannel.startWorkers();
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
                if (test) {
                    //  continue;
                }
                //将bucket分成若干个page 放入channel
                int pageSize = 200;

                //bucket 含 count 个 page
                long count = bucket.getSize() / pageSize;

                //pageNumber 的偏移量
                long numberOffset = bucket.getNumber() * count;

                for (int i = 1; i <= count; i++) {

                    Page page = new Page();
                    page.setPageSize(pageSize);
                    page.setPageNum(numberOffset + i);

                    //todo page.setTableName

                    //todo 不直接传递bucket，因为可能造成脏读
                    QueryRequest queryRequest = new QueryRequest(bucket, page, insertChannel);

                    queryChannel.put(queryRequest);

                    logger.info("查询请求：{}  {}:{}", bucket.getTableName(), bucket.getNumber(), page.getPageNum());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
