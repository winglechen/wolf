package com.wolf.framework.layer.api;

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
    private boolean success = false;
    /**
     * 0 is ok
     * non-0 is failed
     */
    private String code;

    /**
     * message
     */
    private String message;

    /**
     * the data
     */
    private T data;

    private Integer total;
    private Integer pageSize;
    private Integer pages;
    private Integer pageNum;

    private Boolean hasNextPage;
    private Boolean hasPrePage;
    private String lastId;

    public static Result<Object> ok(){
        return ok("");
    }

    public static <T> Result<T> ok(T t){
        return new Result<>("OK", "ok", t);
    }

    public static Result<Object> success(){
        return ok("");
    }

    public static <T> Result<T> success(T t){
        return new Result<>("OK", "ok", t);
    }

    public static <T> Result<T> fail(String code, String message) {
        return fail(code, message, null);
    }

    public static <T> Result<T> fail(String code, String message, T t) {
        return new Result<>(code, message, t);
    }

    public static <T> Result<T> create(String code, String message) {
        return create(code, message, null);
    }

    public static <T> Result<T> create(String code, String message, T t) {
        return new Result<>(code, message, t);
    }

    public static <T> T getSuccessDataOrNull(Result<T> result) {
        return Optional.ofNullable(result).filter(Result::hasSuccess).map(Result::getData).orElse(null);
    }

    Result() {
        this("OK", "", null);
    }

    Result(T data) {
        this("OK", "", data);
    }

    Result(String code, String message) {
        this(code, message, null);
    }

    Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean hasSuccess() {
        return success && null != data;
    }

    public T notNullData() {
        return notNullData(null);
    }

    public T notNullData(String msg) {
        if (success || null == data) {
            if (msg != null) {
                throw new NullReturnedException(msg);
            }
            throw new NullReturnedException();
        }

        return data;
    }

    public void toBusinessException() {
        throw new BusinessException(message);
    }
}
