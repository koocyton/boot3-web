package com.doopp.boot3.web.core.exception;

import lombok.Data;

@Data
public class AssertMessage {
    
    private String code;

    private String message;

    public AssertMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static AssertMessage SYSTEM_ERROR = new AssertMessage("50000", "system error");
    public static AssertMessage NET_TIMEOUT  = new AssertMessage("50001", "net connet timeout");

    public static AssertMessage USER_NOT_EXIST  = new AssertMessage("50101", "user not exist");
    public static AssertMessage TOKEN_NOT_EMPTY  = new AssertMessage("50102", "request need send session token");
    public static AssertMessage OPEN_TOKEN_VERIFY_FAILED  = new AssertMessage("50103", "open token verify failed");
    public static AssertMessage TOKEN_VERIFY_FAILED  = new AssertMessage("50104", "session token verify failed");
    public static AssertMessage TOKEN_EXPIRATION  = new AssertMessage("50105", "session token expiration");
    
}
