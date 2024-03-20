package com.onedot.win.common.util.encrypt.password;

import lombok.Data;
import com.onedot.win.common.util.encrypt.ShaEncrypt;
import com.onedot.win.common.util.encrypt.exception.ShaEncryptFailedException;

/**
 * com.onedot.win.common.util.encrypt
 *
 * @author Wingle
 * @since 2019/11/23 6:26 下午
 **/
@Data
public class Password {

    public static String createSalt() {
        return Salt.create();
    }

    public static String encrypt(String userPassword, String salt) {
        if (null == userPassword || salt == null) {
            throw new IllegalArgumentException("password and salt can't be null");
        }

        String encryptedPassword = userPassword + salt;
        try {
            encryptedPassword = ShaEncrypt.sha512(encryptedPassword).substring(0, 32);
        } catch (Exception e) {
            throw new ShaEncryptFailedException("password encrypt failed");
        }

        return encryptedPassword;
    }
}
