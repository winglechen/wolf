package study.daydayup.wolf.common.util.encrypt;

import lombok.Data;

/**
 * study.daydayup.wolf.common.util.encrypt
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
            throw new IllegalArgumentException("password salt can't be null");
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
