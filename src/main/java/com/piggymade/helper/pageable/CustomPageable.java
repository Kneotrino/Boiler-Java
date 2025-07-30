package com.piggymade.helper.pageable;

import com.piggymade.helper.JsonUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class CustomPageable {

    int pageNumber;
    int pageSize;
    long totalPages;
    long totalElements;

    public CustomPageable(int pageNumber, int pageSize, long totalElements) {
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

    @Override
    public String toString() {
        return JsonUtil.getString(this);
    }
}
