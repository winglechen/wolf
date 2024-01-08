package study.daydayup.wolf.common.util.encrypt;

import study.daydayup.wolf.common.util.lang.CharsetUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaEncrypt {

    public static String sha256(String text, String secret) throws InvalidKeyException, NoSuchAlgorithmException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        return byteArrayToHexString(mac.doFinal(text.getBytes()));
    }

    public static String sha1(String text) throws NoSuchAlgorithmException {
        return encrypt(text, "SHA-1");
    }

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
        byte[] bytes = messageDigest.digest();

        return byteArrayToHexString(bytes);
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
