package com.wolf.framework.middleware.binlog.mysql;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * FieldValue
 *
 * @author: YIK
 * @since: 2022/3/1 10:18 AM
 */
public class FieldValue<T> {
    private String encoding;
    private byte[] bytes;
    private T real;

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public byte[] getValue() {
        return bytes;
    }

    public void setValue(byte[] bytes) {
        this.bytes = bytes;
    }

    public T getReal() {
        return real;
    }

    public void setReal(T real) {
        this.real = real;
    }

    @Override
    public String toString() {
        if (null == getValue()) {
            return "null [binary]";
        }
        if (encoding == null) {
            //return super.toString();
            encoding = "ASCII";
        }
        try {
            if (StringUtils.equals("utf8mb4", encoding)) {
                return new String(getValue(), "utf8");
            } else {
                return new String(getValue(), encoding);
            }
        } catch (UnsupportedEncodingException e) {
            String realEncoding = JDKEncodingMapper.getJDKEncoding(encoding);
            if (null == realEncoding) {
                throw new RuntimeException("Unsupported encoding: " + encoding);
            } else {
                try {
                    return new String(getValue(), realEncoding);
                } catch (UnsupportedEncodingException e1) {
                    throw new RuntimeException("Unsupported encoding: origin " + encoding + ", mapped " + realEncoding);
                }
            }
        }
    }
}
