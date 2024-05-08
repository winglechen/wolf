package com.wolf.framework.middleware.hbase.result;

import lombok.Data;

/**
 * HBaseResult
 *
 * @author rocky
 * @since 2023/7/27 15:12
 **/
@Data
public class HBaseResult<T> {

    private T data;

    private boolean success;

    private String message;

    public static HBaseResult success(){
        HBaseResult result = new HBaseResult();
        result.setSuccess(true);
        return result;
    }

    public static HBaseResult success(Object data){
        HBaseResult result = new HBaseResult();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }
    public static HBaseResult fail(String message){
        HBaseResult result = new HBaseResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}
