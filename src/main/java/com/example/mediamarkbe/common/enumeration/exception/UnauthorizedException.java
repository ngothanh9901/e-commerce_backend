package com.example.mediamarkbe.common.enumeration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BaseApiException {

    public UnauthorizedException(String message) {
        super(message);
    }

}
