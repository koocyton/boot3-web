package com.doopp.boot3.web.core.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PageResponse<T> implements Serializable {
    
    private Long page;

    private Long pageSize;

    private Long total;

    private List<T> records;
}
