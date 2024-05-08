package com.wolf.framework.security.filter.http.detector;

import com.wolf.framework.security.filter.http.wrapper.RequestWrapper;

/**
 * @author weixing
 * @since 2022/11/15 19:40
 */
public interface Detector {
    String getType();
    String getMessage();
    boolean detect(RequestWrapper request);
}
