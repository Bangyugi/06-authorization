package com.bangvan.demologin.service;

import com.bangvan.demologin.exception.ErrorCode;
import lombok.Data;


public class AppException extends RuntimeException {

    public AppException(ErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
