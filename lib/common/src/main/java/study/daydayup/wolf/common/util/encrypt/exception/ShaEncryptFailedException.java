package study.daydayup.wolf.common.util.encrypt.exception;

import lombok.Getter;
import study.daydayup.wolf.common.lang.exception.SystemException;

@Getter
public class ShaEncryptFailedException extends SystemException {
    public ShaEncryptFailedException(String message) {
        super(message);
    }
}
