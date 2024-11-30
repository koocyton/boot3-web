package com.doopp.boot3.web.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssertMessageEnum {
    
    SYSTEM_ERROR("50000", "system error"),
    NET_TIMEOUT("50001", "net connet timeout"),
    ILLEGAL_REQUEST("50001", "net connet timeout"),

    USER_NOT_EXIST("50101", "user not exist"),
    TOKEN_NOT_EMPTY("50102", "request need send session token"),
    OPEN_TOKEN_VERIFY_FAILED("50103", "open token verify failed"),
    TOKEN_VERIFY_FAILED("50104", "session token verify failed"),
    TOKEN_EXPIRATION("50105", "session token expiration"),
    ;

    private final String code;
    private final String message;
}
