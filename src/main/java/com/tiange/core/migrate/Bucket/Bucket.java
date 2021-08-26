package com.tiange.core.migrate.Bucket;

import com.tiange.core.mysql.table.MysqlTable;

public class Bucket {
    MysqlTable mysqlTable;
    long start;
    long end;
    long number;//编号
    long size = 3000;

    public Bucket() {
    }

    public Bucket(Bucket bucket) {
        this.mysqlTable = bucket.getMysqlTable();
        this.number = bucket.getNumber();
        this.size = bucket.getSize();
    }


    public Bucket(MysqlTable mysqlTable, long size) {
        this.mysqlTable = mysqlTable;
        this.size = size;
    }

    /* getter & setter */


    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public MysqlTable getMysqlTable() {
        return mysqlTable;
    }

    public void setMysqlTable(MysqlTable mysqlTable) {
        this.mysqlTable = mysqlTable;
    }
}
