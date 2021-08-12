package com.tiange.core.entity.opengauss.database;

import com.tiange.core.entity.opengauss.table.GaussTable;

import java.util.List;

public class GaussDatabase {
    private String name;

    public GaussDatabase(String name) {
        this.name = name;
    }

    /**
     * 包含的tables
     */
    private List<GaussTable> Tables;


    /* getter & setter */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GaussTable> getTables() {
        return Tables;
    }

    public void setTables(List<GaussTable> tables) {
        Tables = tables;
    }
}
