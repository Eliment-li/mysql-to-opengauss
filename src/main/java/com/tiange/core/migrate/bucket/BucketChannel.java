package com.tiange.core.migrate.bucket;

import com.tiange.core.migrate.PageChannel;
import com.tiange.core.migrate.PageClient;

public class BucketChannel {

    private final PageClient[] pageClientThreadPool;

    public BucketChannel(int threads) {
        pageClientThreadPool = new PageClient[threads];

        PageChannel pageChannel = new PageChannel(3);
        for (int i = 0; i < threads; i++) {
            pageClientThreadPool[i] = new PageClient("pageClient" + i, pageChannel);
        }
    }
}
