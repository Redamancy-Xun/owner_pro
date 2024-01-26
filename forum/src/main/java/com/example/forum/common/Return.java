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
    private String[] result;

    public Return() {
        this.code=0;
    }
}