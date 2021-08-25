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
    // 总记录数
    private long totalCount;
    //总页数
    private long pageCount;
    // 当前的页数，第几页
    private long pageNum;

    private List<Map<String, Object>> pageContent;

    public Page() {

    }

    /**
     * @param pageSize 页的容量
     * @param pageNum  页的编号
     */
    public Page(Integer pageSize, Integer pageNum) {

        if (pageSize == null)
            this.pageSize = Default_PageSize;
        else
            this.pageSize = pageSize;


        if (pageNum == null)
            this.pageNum = Default_Pagenum;
        else
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

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public List<Map<String, Object>> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<Map<String, Object>> pageContent) {
        this.pageContent = pageContent;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
}
