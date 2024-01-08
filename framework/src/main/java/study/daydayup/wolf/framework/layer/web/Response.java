package study.daydayup.wolf.framework.layer.web;

import lombok.Data;

/**
 * com.daydayup.learn.hello.common.framework.layer.web
 *
 * @author Wingle
 * @since 2019/7/11 10:07 PM
 **/

@Data
public class Response<T> {
    private long code;
    private String message;
    private T data;
}
