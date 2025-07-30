package com.piggymade.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.piggymade.helper.JsonUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Kneotrino
 * @since : 23/08/2021
 **/

@Getter
@Setter
public abstract class BaseDto {
    @Override
    public String toString() {
        return JsonUtil.getString(this);
    }

    @JsonIgnore
    private Map<String, Object> extraData = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.extraData;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.extraData.put(name, value);
    }
    @JsonIgnore
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.extraData = additionalProperties;
    }

    public Object selectExtra(String key) {
        return this.extraData.get(key);
    }

}
