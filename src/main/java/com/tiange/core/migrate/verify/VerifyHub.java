package com.tiange.core.migrate.verify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerifyHub extends Thread {
    //查询请求缓冲区
    private final VerifyChannel verifyChannel;

    //日志工具
    private final Logger logger = LoggerFactory.getLogger(VerifyHub.class);

    public VerifyHub(VerifyChannel verifyChannel) {
        this.verifyChannel = verifyChannel;
    }


    public void run() {
        try {
            while (true) {

                VerifyRequest verifyRequest = verifyChannel.takeRequest();

                verifyRequest.execute();
            }

        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}
