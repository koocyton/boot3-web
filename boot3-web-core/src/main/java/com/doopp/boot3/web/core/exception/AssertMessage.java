package com.doopp.boot3.web.core.exception;

import lombok.Data;

@Data
public abstract class AssertMessage {
    
    private String code;

    private String message;

    public AssertMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
