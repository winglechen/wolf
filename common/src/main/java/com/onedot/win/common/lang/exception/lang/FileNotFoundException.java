package com.onedot.win.common.lang.exception.lang;

import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.util.lang.StringUtil;

public class FileNotFoundException extends SystemException {
    private static final String DEFAULT_MESSAGE = "FileNotFoundException";

    public FileNotFoundException() {
        super(500, DEFAULT_MESSAGE);
    }

    public FileNotFoundException(String className) {
        super(500, StringUtil.join("FileNotFoundException: ", className));
    }
}
