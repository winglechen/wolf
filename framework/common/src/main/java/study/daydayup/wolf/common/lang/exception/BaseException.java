package study.daydayup.wolf.common.lang.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Non-thread-safe
 */
@Getter
public abstract class BaseException extends RuntimeException {
    protected long code;
    protected Map<String, Object> data = new HashMap<>();

    public BaseException(String message) {
        this(100, message);
    }

    public BaseException(long code, String message) {
        super(message);
        this.code = code;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void setDataRow(String key, Object value) {
        this.data.put(key, value);
    }

    public void addData(Map<String, Object> map) {
        this.data.putAll(map);
    }
}
