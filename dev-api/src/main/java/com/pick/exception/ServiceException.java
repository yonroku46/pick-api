package com.pick.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private ErrorCode errorCode;

    public ServiceException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}