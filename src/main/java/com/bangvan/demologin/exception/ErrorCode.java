package com.bangvan.demologin.exception;

import lombok.Data;


public enum ErrorCode {
    USERNAME_EXISTED(1001,"Error: User already existed"),
    EMAIL_EXISTED(1002,"Error: Email already existed"),
    UNCATEGORIZED(1003,"Error: Uncategorized exception"),
    FIRSTNAME_INVALID(1004,"Error: First name is required"),
    LASTNAME_INVALID(1005,"Error: Last name is required"),
    EMAIL_INVALID(1006,"Error: Email is invalid"),
    PASSWORD_INVALID(1008,"Error: Password is invalid"),
    USERNAME_INVALID(1009,"Error: Username is required"),
    USERNAME_NOT_EXISTED(1010,"Error: Username not existed"),
    UNAUTHENTICATED(1011,"Error: Unauthenticated"),
    PERMISSION_NOT_EXISTED(1012,"Error: Permission not existed"),
    ROLE_NOT_EXISTED(1013,"Error: Role not existed"),

    ;

    ErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
