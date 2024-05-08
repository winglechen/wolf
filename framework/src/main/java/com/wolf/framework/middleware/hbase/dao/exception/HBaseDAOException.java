package com.wolf.framework.middleware.hbase.dao.exception;

import com.wolf.common.lang.exception.BusinessException;

/**
 * HBaseDAOException
 *
 * @author rocky
 * @since 2023/8/10 16:08
 **/
public class HBaseDAOException extends BusinessException {
    private String message;

    public HBaseDAOException(String message) {
        super(message);
        this.message = message;
    }
}
