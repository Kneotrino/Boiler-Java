package com.piggymade.model.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.piggymade.constant.ResponseConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author : Kneotrino
 * @since : 29/07/2021
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class GlobalResponse<T> extends BaseResponse {


    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public GlobalResponse(T content, int code) {
        setDateTime(new Date());
        setData(content);
        if (content == null) {
            setStatus("Data is null");
        }
        setCode("0" + code);

        if (code > 0) {
            setMessage(ResponseConstant.MESSAGE_FAILED);
        } else {
            setMessage(ResponseConstant.MESSAGE_SUCCESS);
        }

    }

    public GlobalResponse(T content) {
        setDateTime(new Date());
        setData(content);
        if (content == null) {
            setStatus("Data is null");
        }
        setCode(ResponseConstant.STATUS_SUCCESS);
        setMessage(ResponseConstant.MESSAGE_SUCCESS);
    }

    public GlobalResponse(T content, String message) {
        setDateTime(new Date());
        setData(content);

    }

    public GlobalResponse(Integer code, String message) {
        setCode("0" + code);

    }


    public GlobalResponse(T content, String message, int code) {
        setDateTime(new Date());
        setData(content);
        setCode("0" + code);
        if (code > 0) {
            setMessage(ResponseConstant.STATUS_FAILED);
        } else {
            setMessage(message);
        }

    }

    public GlobalResponse() {
        setDateTime(new Date());
    }


    @JsonIgnore
    public T getObject() {
        return (T) getData();
    }
}
