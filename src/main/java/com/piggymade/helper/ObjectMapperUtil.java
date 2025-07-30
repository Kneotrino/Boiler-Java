package com.piggymade.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ObjectMapperUtil {
    private ObjectMapperUtil(){}
    private static ObjectMapper objectMapper = null;
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");


    /**
     *
     * Used to initial object mapper
     *
     */
    public static ObjectMapper getDefaultModelMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }
}
