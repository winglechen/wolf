package study.daydayup.wolf.common.lang.exception;

public abstract class BaseException extends RuntimeException {

    private long code;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(long code, String message) {
        super(message);
        this.code = code;
    }
}
