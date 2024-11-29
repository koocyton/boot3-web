package com.doopp.boot3.web.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesNoStatusEnum {

    YES("YES", "is yes"),
    NO("NO", "is no"),
    ;

    private final String code;
    private final String desc;
}
