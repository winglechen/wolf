package com.wolf.framework.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.wolf.common.util.lang.StringUtil;

import java.io.Serializable;
@Data
@AllArgsConstructor
public final class Response<T> implements Serializable {
    
    private String code;
    
    private String message;
    
    private T data;

    public Response() {
        this("200", "", null);
    }

    public Response(T data) {
        this("200", "", data);
    }

    public Response(String code, String message) {
        this(code, message, null);
    }
    
    public static Response<Object> ok(){
        return ok("");
    }

    public static <T> Response<T> ok(T t){
        return new Response<>("200", "ok", t);
    }

    public static Response<Object> success(){
        return ok("");
    }

    public static <T> Response<T> success(T t){
        return new Response<>("200", "ok", t);
    }

    public static <T> Response<T> fail(String code, String message) {
        return fail(code, message, null);
    }

    public static <T> Response<T> fail(String code, String message, T t) {
        return new Response<>(code, message, t);
    }

    public static <T> Response<T> create(String code, String message) {
        return create(code, message, null);
    }

    public static <T> Response<T> create(String code, String message, T t) {
        return new Response<>(code, message, t);
    }

    public boolean hasSuccess() {
        return StringUtil.isNoneEmpty(code) && code.equals("200") && null != data;
    }

}
