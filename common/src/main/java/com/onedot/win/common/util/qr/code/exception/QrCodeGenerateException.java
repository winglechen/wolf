package com.onedot.win.common.util.qr.code.exception;

import com.onedot.win.common.lang.exception.SystemException;

/**
 * QrCodeGenerateException
 *
 * @author rocky
 * @since 2022/9/29 15:54
 **/
public class QrCodeGenerateException extends SystemException {

    public QrCodeGenerateException(String msg){
        super(msg);
    }
}
