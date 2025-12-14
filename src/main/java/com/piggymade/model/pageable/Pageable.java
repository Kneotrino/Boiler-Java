package com.piggymade.model.pageable;


import com.piggymade.model.BaseDto;

import java.util.List;

public class Pageable<T> extends BaseDto {

    List<T> content;
    PageableInfo info;
    public Pageable(List<T> content, int pageNumber, int pageSize, long totalElements) {
        this.content = content;
        this.info = new PageableInfo(pageNumber, pageSize, totalElements);
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public PageableInfo getInfo() {
        return info;
    }

    public void setInfo(PageableInfo info) {
        this.info = info;
    }

}
