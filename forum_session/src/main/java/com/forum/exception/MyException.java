package com.forum.exception;

import com.forum.common.EnumExceptionType;
import lombok.Getter;

@Getter
public class MyException extends RuntimeException {

    private EnumExceptionType enumExceptionType;

    private Object errorMsg;

    public MyException() {
    }

    public MyException(EnumExceptionType enumExceptionType) {
        this.enumExceptionType = enumExceptionType;
    }

    public MyException(EnumExceptionType commonErrorCode, Object errorMsg) {
        this.enumExceptionType = commonErrorCode;
        this.errorMsg = errorMsg;
    }
}
