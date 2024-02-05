package com.forum.exception;

import com.forum.common.EnumExceptionType;
import lombok.Getter;

@Getter
public class MyException extends RuntimeException {

    private EnumExceptionType enumExceptionType;

    public MyException() {
    }

    public MyException(EnumExceptionType enumExceptionType) {
        this.enumExceptionType = enumExceptionType;
    }
}
