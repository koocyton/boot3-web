package com.doopp.boot3.web.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SwitchStatusEnum {

    OPEN("OPENED", "is opened"),
    CLOSE("CLOSED", "is closed"),
    ;

    private final String code;
    private final String desc;
}
