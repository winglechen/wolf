package com.wolf.framework.rpc;

import lombok.Data;
import com.wolf.common.lang.exception.BusinessException;
import com.wolf.common.lang.exception.api.NullReturnedException;

import java.io.Serializable;
import java.util.Optional;

/**
 * com.wolf.framework.rpc
 *
 * @author Wingle
 * @since 2019/11/6 4:18 下午
 **/
@Data
public final class Result<T> implements Serializable {
    /**
     * 0 is ok
     * non-0 is failed
     */
    private long code;

    /**
     * to replace code
     * because string is more meaningful
     *
     * null for correct result
     * brief exception name for error
     */
    private String error;

    /**
     * message
     */
    private String message;

    /**
     * the data
     */
    private T data;

    public static Result<Object> ok(){
        return ok("");
    }

    public static <T> Result<T> ok(T t){
        return new Result<>(0, "ok", t);
    }

    public static Result<Object> success(){
        return ok("");
    }

    public static <T> Result<T> success(T t){
        return new Result<>(0, "ok", t);
    }

    public static <T> Result<T> fail(long code, String message) {
        return fail(code, message, null);
    }

    public static <T> Result<T> fail(long code, String message, T t) {
        return new Result<>(code, message, t);
    }

    public static <T> Result<T> create(long code, String message) {
        return create(code, message, null);
    }

    public static <T> Result<T> create(long code, String message, T t) {
        return new Result<>(code, message, t);
    }

    public static <T> T getSuccessDataOrNull(Result<T> result) {
        return Optional.ofNullable(result).filter(Result::hasSuccess).map(Result::getData).orElse(null);
    }

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
    }

    public boolean hasSuccess() {
        return 0 == code && null != data;
    }

    public T notNullData() {
        return notNullData(null);
    }

    public T notNullData(String msg) {
        if (0 != code || null == data) {
            if (msg != null) {
                throw new NullReturnedException(msg);
            }
            throw new NullReturnedException();
        }

        return data;
    }

    public void toBusinessException() {
        throw new BusinessException(code, message);
    }
}
