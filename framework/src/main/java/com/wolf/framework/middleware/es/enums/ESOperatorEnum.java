package com.wolf.framework.middleware.es.enums;

import lombok.Getter;
import com.wolf.common.lang.enums.CodeBasedEnum;

@Getter
public enum ESOperatorEnum implements CodeBasedEnum {
    EQUAL(1, "eq"),
    IN(2, "in"),

    /**
     * @doc https://www.elastic.co/guide/en/elasticsearch/reference/7.17/query-dsl-range-query.html
     */
    GREATER_THAN(10, "gt"),
    GREATER_THAN_OR_EQUAL_TO(11, "gte"),
    LESS_THAN(12, "lt"),
    LESS_THAN_OR_EQUAL_TO(13, "let");

    private final int code;
    private final String name;

    ESOperatorEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}