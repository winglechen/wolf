package com.onedot.win.common.lang.exception.lang;

import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.util.lang.StringUtil;

/**
 * com.onedot.win.common.lang.exception.lang
 *
 * @author Wingle
 * @since 2021/11/7 下午10:13
 **/
public class ClassNotFoundException extends SystemException {
    private static final String DEFAULT_MESSAGE = "ClassNotFoundException";

    public ClassNotFoundException() {
        super(500, DEFAULT_MESSAGE);
    }

    public ClassNotFoundException(String className) {
        super(500, StringUtil.join("ClassNotFoundException: ", className));
    }
}
