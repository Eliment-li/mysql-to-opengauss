package com.tiange.core.migrate;

import com.tiange.core.utils.database.jdbc.Page;

public class PageRequest extends Request {

    private final Page page;

    public PageRequest(Page page) {
        this.page = page;
    }

    @Override
    public void execute() {
        //todo execute
    }
}
