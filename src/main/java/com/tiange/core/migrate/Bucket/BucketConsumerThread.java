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
        insertChannel = new InsertChannel(2);

    }


    public void run() {
        //启动工人线程
        queryChannel.startWorkers();
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
                //logger.info("获取bucket{}", bucket.getNumber());
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
                    page.setMysqlTable(bucket.getMysqlTable());

                    //将bucket信息复制一份传递过去
                    // Bucket newBucket=new Bucket(bucket) ;
                    QueryRequest queryRequest = new QueryRequest(page, insertChannel);

                    logger.info("put");
                    try {
                        queryChannel.put(queryRequest);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    logger.info("查询请求：{}  {}:{}", page.getMysqlTable().getTable_name(), bucket.getNumber(), page.getPageNum());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
