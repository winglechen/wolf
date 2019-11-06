package study.daydayup.wolf.framework.rpc;

import lombok.Data;
import study.daydayup.wolf.common.lang.exception.BusinessException;
import study.daydayup.wolf.common.lang.exception.NullReturnedException;

import java.io.Serializable;

/**
 * study.daydayup.wolf.framework.rpc
 *
 * @author Wingle
 * @since 2019/11/6 4:18 下午
 **/
@Data
public final class Result<T> implements Serializable {
    private long code;
    private String message;
    private T data;

    private String exception;

    Result() {
        this(0, "", null);
    }

    Result(T data) {
        this(0, "", data);
    }

    Result(long code, String message) {
        this(code, message, null);
    }

    Result(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.exception = null;
    }

    public boolean isSuccess() {
        return 0 == code;
    }

    public T getNotNullData() {
        if (null == data) {
            throw new NullReturnedException("Result.getNotNullData return null");
        }

        return data;
    }

    public void toBusinessException() {
        throw new BusinessException(code, message);
    }
}
