package study.daydayup.wolf.framework.middleware.hbase.dao.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * HBaseDAOException
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/8/10 16:08
 **/
public class HBaseDAOException extends BusinessException {
    private String message;

    public HBaseDAOException(String message) {
        super(message);
        this.message = message;
    }
}
