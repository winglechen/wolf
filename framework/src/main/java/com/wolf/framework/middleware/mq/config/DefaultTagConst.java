package com.wolf.framework.middleware.mq.config;

import com.wolf.framework.layer.domain.Const;

/**
 * com.wolf.framework.middleware.mq.constant
 *
 * @author Wingle
 * @since 2021/12/19 下午12:56
 **/
public class DefaultTagConst implements Const {
    private DefaultTagConst() {}

    public static final String ALL = "*";

    public static final String CREATED = "CREATED";

    public static final String SUCCEEDED = "SUCCEEDED";
    public static final String FAILED = "FAILED";
    public static final String COMPLETED = "COMPLETED";
    public static final String QUERY = "QUERY";

    public static final String POSTPONED = "POSTPONED";
    public static final String QUEUED = "QUEUED";

}
