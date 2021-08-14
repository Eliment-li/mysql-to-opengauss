package com.tiange.core.opengauss.database;

import com.tiange.core.opengauss.key.GaussKey;
import com.tiange.core.opengauss.table.GaussTable;

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

    /**
     * 包含的Keys
     */
    private List<GaussKey> keys;


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

    public List<GaussKey> getKeys() {
        return keys;
    }

    public void setKeys(List<GaussKey> keys) {
        this.keys = keys;
    }
}
