package study.daydayup.wolf.common.util.encrypt;

import lombok.NonNull;
import study.daydayup.wolf.common.util.encrypt.exception.Md5EncryptFailedException;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * study.daydayup.wolf.common.util.encrypt
 *
 * @author Wingle
 * @since 2020/3/16 8:28 下午
 **/
public class MD5Util {
    public static String md5(@NonNull String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new Md5EncryptFailedException(str);
        }
    }


}
