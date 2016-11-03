package com.saferich.core.util;

public class PagePagination {
    // 页大小 (每页显示记录条数)
    private Integer maxRows;

    // 页索引 (当前页数)
    private Integer pageIndex;

    // 总页数
    private Integer pageTotalCount;

    // 总条数
    private Integer totalCount;

    // 起始位置
    private Integer startIndex;

    public PagePagination(String maxRows, String pageIndex) {
        this.maxRows = (StringUtils.isEmpty(maxRows) ? null : Integer.parseInt(maxRows));
        this.pageIndex = (StringUtils.isEmpty(pageIndex) ? null : Integer.parseInt(pageIndex));
        if (this.maxRows != null && this.pageIndex != null) {
            Integer temp = (this.pageIndex - 1) * this.maxRows;
            if (temp < 0) {
                temp = 0;
            }
            this.startIndex = temp;
            this.maxRows = Integer.parseInt(maxRows);
            this.pageIndex = Integer.parseInt(pageIndex);
        }
    }

    public PagePagination(Integer maxRows, Integer pageIndex) {
        Integer temp = (pageIndex - 1) * maxRows;
        if (temp < 0) {
            temp = 0;
        }
        this.startIndex = temp;
        this.maxRows = maxRows;
    }

    public Integer getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

}
