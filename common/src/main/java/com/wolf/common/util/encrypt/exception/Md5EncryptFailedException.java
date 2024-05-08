package com.wolf.common.util.encrypt.exception;

import lombok.Getter;
import com.wolf.common.lang.exception.SystemException;
import com.wolf.common.util.lang.StringUtil;

@Getter
public class Md5EncryptFailedException extends SystemException {
    public Md5EncryptFailedException(String str) {
        super(StringUtil.joinWith(StringUtil.BLANK, "md5(", str, ") failed"));
    }
}
