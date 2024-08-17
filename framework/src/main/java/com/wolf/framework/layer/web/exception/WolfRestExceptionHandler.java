package com.wolf.framework.layer.web.exception;

import com.wolf.common.lang.exception.BusinessException;
import com.wolf.common.lang.exception.ExceptionUtil;
import com.wolf.common.lang.exception.SystemException;
import com.wolf.common.lang.exception.api.NoPermissionException;
import com.wolf.common.lang.exception.api.NotLoggedInException;
import com.wolf.common.lang.exception.lang.IllegalArgumentException;
import com.wolf.framework.layer.api.result.Result;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ConditionalOnProperty(value = {"wolf.exception.enableRestHandler"}, havingValue = "true")
public class WolfRestExceptionHandler {

    @ExceptionHandler(value = NoPermissionException.class)
    public Result<String> handlePermissionException(Exception ex) {
        String code = "NO_PERMISSION";

        Result<String> result = Result.failure(code, ex.getMessage());
        result.setCodeType(40);

        return result;
    }

    @ExceptionHandler(value = NotLoggedInException.class)
    public Result<String> handleLoginException(Exception ex) {
        String code = "NEED_LOGIN";

        Result<String> result = Result.failure(code, ex.getMessage());
        result.setCodeType(30);

        return result;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<String> handleArgumentException(Exception ex) {
        String code = "ILLEGAL_ARGUMENT";

        Result<String> result = Result.failure(code, ex.getMessage());
        result.setCodeType(50);

        return result;
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result<String> handleBusinessException(Exception ex) {
        String code = ExceptionUtil.getName(ex);

        Result<String> result = Result.failure(code, ex.getMessage());
        result.setCodeType(20);

        return result;
    }

    @ExceptionHandler(value = {SystemException.class, Exception.class})
    public Result<String> handleSystemException(Exception ex) {
        String code = "NETWORK_ERROR";

        Result<String> result = Result.failure(code, ex.getMessage());
        result.setCodeType(10);

        return result;
    }

}
