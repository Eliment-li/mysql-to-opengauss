package com.tiange.core.utils.database.jdbc;

import java.util.List;
import java.util.Map;

/**
 * Page 对象用于分页查询
 */
public class Page {

    private static int Default_Pagenum = 1;
    private static int Default_PageSize = 20;

    // 每页的记录数
    private int pageSize;
    // 获取的所有的记录数
    private long totalCount;
    // 当前的页数，第几页
    private int pageNum;

    private List<Map<String, Object>> pageContent;

    public Page() {

    }

    public Page(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    /* getter & setter */

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<Map<String, Object>> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<Map<String, Object>> pageContent) {
        this.pageContent = pageContent;
    }
}
