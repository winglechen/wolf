package study.daydayup.wolf.common.lang.exception;

import lombok.Data;

@Data
public class BusinessException extends BaseException {
    private String exception;

    public BusinessException(String message) {
        super(1000, message);
    }

    public BusinessException(long code, String message) {
        super(code, message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}