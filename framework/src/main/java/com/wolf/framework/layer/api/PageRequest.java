package com.wolf.framework.layer.api;

import lombok.AllArgsConstructor;
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
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest implements Request {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int MAX_PAGE_SIZE = 1000;
    private static final int DEFAULT_PAGE_NUM = 1;

    private Integer pageSize;
    // prePage: -1; nextPage: 1;
    private Integer pageNum;

    private Integer order;
    private String orderBy;
    private Long orderValue;

    public void init() {
        if (pageNum == null) {
            pageNum = DEFAULT_PAGE_NUM;
        }

        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
    }

    public Integer getPageNum() {
        if (pageNum != null) {
            return pageNum;
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
        return (getPageNum() - 1) * getPageSize().longValue();
    }
}
