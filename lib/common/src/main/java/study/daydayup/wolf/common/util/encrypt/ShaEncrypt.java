package study.daydayup.wolf.common.util.encrypt;

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
        messageDigest.update(text.getBytes());
        byte byteBuffer[] = messageDigest.digest();

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < byteBuffer.length; i++) {
            String hex = Integer.toHexString(0xff & byteBuffer[i]);
            if (hex.length() == 1) {
                stringBuffer.append('0');
            }
            stringBuffer.append(hex);
        }
        // 得到返回結果
        return stringBuffer.toString();
    }
}
