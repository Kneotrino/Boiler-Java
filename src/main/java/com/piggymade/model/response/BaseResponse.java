package com.piggymade.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.piggymade.model.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Kneotrino
 * @since : 29/07/2021
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class BaseResponse extends BaseDto {

    private Object data;
    private String status;
    private String message; // Could be String or BoostMessageResponse
    private String code;

}
