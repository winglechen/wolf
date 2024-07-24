package com.wolf.dbclient.sql.builder;

import com.wolf.dbclient.enums.OrderEnum;
import lombok.NonNull;

/**
 * com.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 6:23 下午
 **/
public class OrderBuilder {
    public static String orderBy(@NonNull String column, OrderEnum order, boolean isFirstOrder) {
        StringBuilder sql = new StringBuilder();
        if (isFirstOrder) {
            sql.append(SqlKeyword.ORDER_BY);
        } else {
            sql.append(SqlKeyword.COMMA);
        }
        sql.append(column);
        sql.append(SqlKeyword.BLANK).append(order.getName());

        return sql.toString();
    }
}
