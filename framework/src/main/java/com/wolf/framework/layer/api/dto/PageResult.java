package com.wolf.framework.layer.api.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageResult<T> extends Result<T> implements Serializable {
    private List<T> list;

    private Integer total;
    private Integer pageSize;
    private Integer pages;
    private Integer pageNum;

    private Boolean hasNextPage;
    private Boolean hasPrePage;
    private String lastId;
}
