package com.onedot.win.common.io.sql.builder;

import com.onedot.win.common.util.lang.StringUtil;

/**
 * com.onedot.win.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 4:24 下午
 **/
public class SqlKeyword {
    public static final String DEFAULT_KEY = "id";
    public static final String DEFAULT_COLUMNS = "*";
    public static final String COUNT_KEY = "count";
    public static final String DEFAULT_COUNT = StringUtil.join(" count(*) as ", COUNT_KEY, " ");
    public static final int DEFAULT_LIMIT = 10;

    public static final String UNION_ALL = " UNION ALL ";
    public static final String UNION = " UNION ";

    public static final String ON = " ON ";
    public static final String LEFT_JOIN = " LEFT JOIN ";
    public static final String RIGHT_JOIN = " RIGHT JOIN ";
    public static final String INNER_JOIN = " INNER JOIN ";
    public static final String OUTER_JOIN = " OUTER JOIN ";
    public static final String FULL_OUTER_JOIN = " FULL OUTER JOIN ";


    public static final String SELECT = "SELECT ";
    public static final String INSERT = "INSERT INTO ";
    public static final String INSERT_IGNORE = "INSERT IGNORE INTO";
    public static final String UPDATE = "UPDATE ";
    public static final String DELETE = "DELETE FROM ";

    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String AND = " AND ";
    public static final String HAVING = " HAVING ";

    public static final String SET = " SET ";
    public static final String DUPLICATE_UPDATE = " ON DUPLICATE KEY UPDATE ";
    public static final String VALUES = " VALUES ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String GROUP_BY = " GROUP BY ";
    public static final String LIMIT = " LIMIT ";

    public static final String COMMA = ", ";
    public static final String BLANK = " ";

    public static final String GREATER_THAN = ">";
    public static final String LESS_THAN = "<";
    public static final String EQUAL = "=";
    public static final String NOT_EQUAL = "<>";

    public static final String QUESTION_MARK = "?";

    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";
}
