package com.piggymade.model.pageable;


import com.piggymade.helper.JsonUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PageableInfo {

    int pageNumber;
    int pageSize;
    long totalPages;
    long totalElements;

    public PageableInfo(int pageNumber, int pageSize, long totalElements) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;

        if ((totalElements > 0) && (totalElements < pageSize)) {
            this.totalPages = 1;
        } else {
            this.totalPages = new BigDecimal(totalElements)
                    .divide(new BigDecimal(pageSize), RoundingMode.UP)
                    .longValue();
        }
        this.totalElements = totalElements;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return JsonUtil.getString(this);
    }
}
