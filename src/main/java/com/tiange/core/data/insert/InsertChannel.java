package com.tiange.core.data.insert;

import com.tiange.core.utils.others.SystemProperties;

import java.util.concurrent.ArrayBlockingQueue;


/**
 * 生产者-消费者模式中的 Channel 角色
 * 负责传递工作请求，以及保存工程线程
 */
public class InsertChannel extends ArrayBlockingQueue<InsertRequest> {


    private final InsertHub[] threadPool;

    public InsertChannel(int threads) {

        //缓冲区容量
        super(Integer.parseInt(SystemProperties.dataMigrate("insertChannelCapacity")));

        //线程池大小
        threadPool = new InsertHub[threads];

        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new InsertHub("Worker-" + i, this);
        }

    }

    // 启动 工人线程
    public void startWorkers() {

        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }

    }

    public void putRequest(InsertRequest request) throws InterruptedException {
        super.put(request);
    }

    public InsertRequest takeRequest() throws InterruptedException {
        InsertRequest request = super.take();
        return request;
    }
}
