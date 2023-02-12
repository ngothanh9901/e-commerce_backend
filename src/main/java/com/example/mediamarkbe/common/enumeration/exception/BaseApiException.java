package com.example.mediamarkbe.common.enumeration.exception;

import com.example.mediamarkbe.common.constant.RestCommonConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Collections;

@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true, chain = true)
public class BaseApiException extends RuntimeException {

    @Getter
    private int code = RestCommonConstant.ErrorCode.DEFAULT;

    @Getter
    private String displayMessage;

    @Getter
    private Object extraData = Collections.EMPTY_MAP;

    public BaseApiException() {
        super();
    }

    public BaseApiException(String message) {
        super(message);
    }

}
