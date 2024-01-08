package study.daydayup.wolf.common.util.qr.code.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * QrCodeGenerateException
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2022/9/29 15:54
 **/
public class QrCodeGenerateException extends SystemException {

    public QrCodeGenerateException(String msg){
        super(msg);
    }
}
