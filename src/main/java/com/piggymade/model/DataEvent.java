package com.piggymade.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@Data
public class DataEvent<T> extends BaseDto {
    private String message;
    private Map<String,String> meta;
    private T data;
    private long timestamp = System.currentTimeMillis();

}
