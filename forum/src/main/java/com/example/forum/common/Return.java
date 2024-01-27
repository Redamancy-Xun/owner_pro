package com.example.forum.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Return {
    private int code;
    private String message;
    private Object result;

    public Return() {
        this.code=0;
    }
}