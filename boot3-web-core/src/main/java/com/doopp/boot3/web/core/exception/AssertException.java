package com.doopp.boot3.web.core.exception;

public class AssertException extends RuntimeException {
    
    private String code;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;;
    }

    public AssertException(String code, String message) {
        super(message);
        this.code = code;
    }

    public AssertException(AssertMessage assertMessage) {
        super(assertMessage.getMessage());
        this.code = assertMessage.getCode();
    }

    public AssertException(Exception e) {
        super(e.getMessage());
        this.code = "500";
    }

    public AssertException(String message) {
        super(message);
        this.code = "500";
    }
}
