package com.wolf.framework.layer.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * com.wolf.framework.rpc.page
 *
 * @author Wingle
 * @since 2020/1/4 5:09 下午
 **/
@Data
@SuperBuilder
@NoArgsConstructor
public class PageRequest implements Request {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int MAX_PAGE_SIZE = 1000;
    private static final int DEFAULT_PAGE_NUM = 1;

    private Integer pageSize;
    private Integer current;

    private String orderBy;

    public void init() {
        if (current == null) {
            current = DEFAULT_PAGE_NUM;
        }

        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
    }

    public Integer getCurrent() {
        if (current != null) {
            return current;
        }

        return DEFAULT_PAGE_NUM;
    }

    public Integer getPageSize() {
        if (pageSize != null) {
            return pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize;
        }

        return DEFAULT_PAGE_SIZE;
    }

    public Long getOffset() {
        return (getCurrent() - 1) * getPageSize().longValue();
    }
}
