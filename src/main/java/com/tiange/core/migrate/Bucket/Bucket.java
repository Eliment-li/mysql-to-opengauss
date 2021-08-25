package com.tiange.core.migrate.Bucket;

public class Bucket {
    String TableName;
    long start;
    long end;
    long number;//编号
    long size = 1000;

    public Bucket() {
    }

    public Bucket(String tableName, long size) {
        TableName = tableName;
        this.size = size;
    }

    /* getter & setter */

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

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
}
