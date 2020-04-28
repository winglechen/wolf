package study.daydayup.wolf.common.util.encrypt;

import com.google.common.base.Charsets;
import study.daydayup.wolf.common.util.lang.CharsetUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaEncrypt {

    public static String sha256(String text) throws NoSuchAlgorithmException {
        return encrypt(text, "SHA-256");
    }

    public static String sha512(String text) throws NoSuchAlgorithmException {
        return encrypt(text, "SHA-512");
    }

    private static String encrypt(String text, String algorithm) throws NoSuchAlgorithmException {
        if (text == null || text.length() == 0) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(text.getBytes(CharsetUtil.UTF_8));
        byte[] byteBuffer = messageDigest.digest();

        StringBuilder stringBuffer = new StringBuilder();
        for (byte b : byteBuffer) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                stringBuffer.append('0');
            }
            stringBuffer.append(hex);
        }
        // 得到返回結果
        return stringBuffer.toString();
    }
}
