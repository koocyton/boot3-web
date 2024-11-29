package com.doopp.boot3.web.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimerTypeEnum {

    WEEKLY("WEEKLY", "every week"),
    MONTHLY("MONTHLY", "every month"),
    DAILY("DAILY", "every day"),
    FIXED("FIXED", "fixed date time"),
    NOW("NOW", "at now"),
    ;

    private final String code;
    private final String desc;
}
