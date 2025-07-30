package com.piggymade.helper.pageable;

import lombok.Data;

import java.util.List;

@Data
public class CustomPage<T> {

    List<T> content;
    CustomPageable pageable;

    String message;

    public CustomPage(List<T> content, int pageNumber, int pageSize, long totalElements) {
        this.content = content;
        this.pageable = new CustomPageable(pageNumber, pageSize, totalElements);
    }

    public CustomPage(String message) {
        this.message = message;
    }
}
