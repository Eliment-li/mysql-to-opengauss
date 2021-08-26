package com.tiange.core.migrate.insert;

import com.tiange.core.migrate.Bucket.Bucket;
import com.tiange.core.utils.database.jdbc.Page;

public class InsertRequest {

    private final Bucket bucket;
    private final Page page;

    public InsertRequest(Bucket bucket, Page page) {
        this.bucket = bucket;
        this.page = page;
    }

    public void execute() {
        try {
            //插入数据
            insert2OpenGauss();

            //将数据放入校验队列中
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //将数据插入到OpenGauss数据库中，
    private void insert2OpenGauss() {

    }
}
