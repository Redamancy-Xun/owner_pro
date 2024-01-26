package com.example.forum.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Return {
    private int code;
    private String message;
    private String[] result;

    public Return(int code, String message, String[] result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Return() {
        this.code=0;
    }
}