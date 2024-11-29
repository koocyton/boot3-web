package com.doopp.boot3.web.core.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class PageRequest implements Serializable {
    
    private Long page;

    private Long pageSize;
}
