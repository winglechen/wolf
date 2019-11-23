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
    private String password;
    private String salt;


    public static Password encrypt(String userPassword) {
        Password password = new Password();

        String salt = Salt.create();
        password.setSalt(salt);

        String encryptedPassword = userPassword + salt;
        try {
            encryptedPassword = ShaEncrypt.sha512(encryptedPassword).substring(0, 32);
            password.setPassword(encryptedPassword);
        } catch (Exception e) {
            throw new ShaEncryptFailedException("password encrypt failed");
        }

        return password;
    }
}
