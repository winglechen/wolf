package com.onedot.win.framework.exception.handler;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.onedot.win.common.lang.exception.BaseException;
import com.onedot.win.common.lang.exception.BusinessException;
import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.framework.exception.InvalidRequestException;
import com.onedot.win.framework.exception.NoPermissionException;
import com.onedot.win.framework.rpc.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * Handle and format controller-lay exception to json of Result.class
 *
 * @author: YIK
 * @since: 2022/2/21 12:54 PM
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String DEFAULT_SYSTEM_EXCEPTION_MESSAGE = "network error, please try later";

//    @ExceptionHandler(value = Throwable.class)
//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result<String> handleThrowableException(Throwable e, HttpServletRequest httpRequest) {
//
//        String error = "Runtime";
//        String friendlyMessage = "UnknownRuntimeError";
//        int code = 500;
//        try {
//            String message = e.toString();
//            if (e.getCause() != null) {
//                message = e.getCause().toString();
//            }
//
//            String mainMessage = "RuntimeException";
//            if (message.contains("\n")) {
//                mainMessage = message.substring(0, StringUtils.ordinalIndexOf(message, "\n", 1));
//            }
//            error = mainMessage.substring(StringUtils.lastIndexOf(mainMessage, ".") + 1, mainMessage.lastIndexOf("Exception"));
//
//            String tmp = StringUtils.substringBetween(message, "\n", "\n");
//            if (tmp != null) {
//                friendlyMessage = tmp;
//            } else {
//                friendlyMessage = message.substring(message.indexOf(":") + 1);
//            }
//        } catch (Throwable t) {
//            log.warn("process handleRuntimeException occur error", t);
//        } finally {
//            Result<String> result = Result.fail(code, friendlyMessage);
//            result.setError(error);
//            if (EnvHolderUtil.isDev()) {
//                log.error("{} {} {} {}", httpRequest.getRequestURI(), code, error, friendlyMessage, e);
//            } else {
//                log.error("{} {} {} {}", httpRequest.getRequestURI(), code, error, friendlyMessage);
//            }
//            return result;
//        }
//
//    }


    @ExceptionHandler(value = SystemException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleSystemException(SystemException e, HttpServletRequest httpRequest) {
        return convert(e, httpRequest);
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleBusinessException(BusinessException e, HttpServletRequest httpRequest) {
        return convert(e, httpRequest);
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleBaseException(BaseException e, HttpServletRequest httpRequest) {
        return convert(e, httpRequest);
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handle(Throwable e, HttpServletRequest httpRequest) {
        return convert(e, httpRequest);
    }

    @ExceptionHandler(value = NoPermissionException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Result<String> handleNoPermissionException(NoPermissionException e, HttpServletRequest httpRequest) {
        return convert(e, httpRequest);
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Result<String> handleInvalidRequestException(InvalidRequestException e, HttpServletRequest httpRequest) {
        return convert(e, httpRequest);
    }

    /**
     * 处理controller参数校验失败
     * Method Argument @Validated
     * Field @Notnull
     * @param e MethodArgumentNotValidException
     * @param httpRequest HttpServletRequest
     * @return Result
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpRequest) {
        BindingResult bindingResult = e.getBindingResult();
        String msg = e.getMessage();
        if (bindingResult.hasErrors()) {
            msg = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        }
        log.error("Global exception handle argument valid exception,uri:{} msg:{}", httpRequest.getRequestURI(), msg);
        return Result.fail(HttpStatus.BAD_REQUEST.value(), msg);
    }


    @NotNull
    private Result<String> convert(Throwable e, HttpServletRequest httpRequest) {
        long code;
        String msg;
        if (e instanceof BusinessException) {
            code = ((BusinessException )e).getCode();
            msg = e.getMessage();
        } else {
            code = 500;
            msg = DEFAULT_SYSTEM_EXCEPTION_MESSAGE;
        }

        String exceptionName = e.getClass().getSimpleName();
        exceptionName = exceptionName.substring(0, exceptionName.lastIndexOf("Exception"));
        String error = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, exceptionName);

        Result<String> result = Result.fail(code, msg);
        result.setError(error);

        log.error("{} {} {} {}", httpRequest.getRequestURI(), code, exceptionName, e.getMessage(), e);

        return result;
    }

}
