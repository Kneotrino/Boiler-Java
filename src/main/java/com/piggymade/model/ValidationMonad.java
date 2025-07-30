package com.piggymade.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.piggymade.helper.JsonUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Kneotrino
 * @since : 22/08/2022
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationMonad<T> {

    private String message;
    private String table;
    private String uuid;
    private Long id;
    private boolean error = false;
    private boolean duplicate = false;

    @JsonIgnore
    private T data;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    @Override
    public String toString() {
        return JsonUtil.getString(this);
    }

}
