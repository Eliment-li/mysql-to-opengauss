package com.tiange.core.data.verify;

import com.tiange.core.data.query.QueryChannel;
import com.tiange.core.utils.others.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;

public class VerifyChannel extends ArrayBlockingQueue<VerifyRequest> {

    private final VerifyHub[] threadPool;

    //日志工具
    private Logger logger = LoggerFactory.getLogger(QueryChannel.class);

    public VerifyChannel(int threads) {
        //缓冲区容量
        super(Integer.parseInt(SystemProperties.dataMigrate("verifyChannelCapacity")));
        //线程池大小
        threadPool = new VerifyHub[threads];

        for (int i = 0; i < threads; i++) {
            threadPool[i] = new VerifyHub(this);
        }
    }


    //启动工作线程
    public void startWorkers() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    public void putRequest(VerifyRequest request) throws InterruptedException {
        super.put(request);
    }

    public VerifyRequest takeRequest() throws InterruptedException {
        VerifyRequest request = super.take();
        return request;
    }

}
