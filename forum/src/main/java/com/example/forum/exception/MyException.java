package com.example.forum.exception;

import lombok.Getter;
import com.example.forum.common.EnumExceptionType;

@Getter
public class MyException extends RuntimeException {

    private EnumExceptionType enumExceptionType;

    public MyException() {
    }

    public MyException(EnumExceptionType enumExceptionType) {
        this.enumExceptionType = enumExceptionType;
    }
}
