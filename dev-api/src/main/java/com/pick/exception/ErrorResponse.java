package com.pick.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse extends RuntimeException {

    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }
}