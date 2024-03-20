package com.onedot.win.framework.rpc.exception;

import com.onedot.win.common.lang.exception.BusinessException;
import com.onedot.win.common.lang.exception.SystemException;

import java.util.HashMap;
import java.util.Map;

/**
 * com.onedot.win.framework.rpc.exception
 *
 * @author Wingle
 * @since 2019/11/5 2:19 下午
 **/
public final class WolfExceptionCodec {
    private static final String EXCEPTION = "E_NAME";
    private static final String CODE = "E_CODE";
    private static final String MESSAGE = "E_MSG";
    private static final String DATA = "E_DATA";

    public static Map<String, String> encode(BusinessException e) {
        Map<String, String> result = new HashMap<>();
        result.put(EXCEPTION, e.getClass().getName() );
        result.put(CODE, Long.toString( e.getCode() ) );
        result.put(MESSAGE, (String) e.getMessage() );
        result.put(DATA, e.getData().toString());

        return result;
    }

    public static BusinessException decode(Map<String, String> result) {
        if (null == result.get(EXCEPTION)
                || null == result.get(CODE)
                || null == result.get(MESSAGE)
            ) {
            return null;
        }

        return doDecoding(result);
    }

    public static void clearExceptionKeys(Map<String, String> result) {
        result.remove(EXCEPTION);
        result.remove(CODE);
        result.remove(MESSAGE);
        result.remove(DATA);
    }


    private static BusinessException doDecoding(Map<String, String> result) {
        long code = Long.parseLong( result.get(CODE) );
        String message = result.get(MESSAGE);
        BusinessException e = new BusinessException(code, message);

        String exceptionName = result.get(EXCEPTION);
        Map<String, String> data = new HashMap<>();
        data.put("exception", exceptionName);

        if(null != result.get(DATA)) {
            //TODO:
        }
        e.setData(data);

        return e;
    }
}
