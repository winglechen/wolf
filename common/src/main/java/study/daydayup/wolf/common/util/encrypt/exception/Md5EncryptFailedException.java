package study.daydayup.wolf.common.util.encrypt.exception;

import lombok.Getter;
import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.util.lang.StringUtil;

@Getter
public class Md5EncryptFailedException extends SystemException {
    public Md5EncryptFailedException(String str) {
        super(StringUtil.joinWith(StringUtil.BLANK, "md5(", str, ") failed"));
    }
}
