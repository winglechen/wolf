package study.daydayup.wolf.framework.security.authentication;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.common.util.qr.code.QrCodeUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * TOTPAuthentication
 * 1.generateSecret save secret
 * 2.getQrUrl generateQrCode scan it
 * 3.get totp-code
 * 4.checkCode with secret and totp-code
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2022/9/28 18:20
 **/
public class TOTPAuthentication {

    private static final String SEED = "202209291519ONION-PAY";

    private static final int SECRET_SIZE = 15;

    public static final String TOTP_URL = "otpauth://totp/%s?secret=%s";


    /**
     * generate random key
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateSecret() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(Base64.decodeBase64(SEED));
        byte[] buffer = random.generateSeed(SECRET_SIZE);
        byte[] encodeKey = new Base32().encode(buffer);
        return new String(encodeKey);
    }

    public static String getQrUrl(String user, String secret) {
        return String.format(TOTP_URL, user, secret);
    }

    /**
     * @param user
     * @param secret
     * @return image base64
     */
    public static String generateQrCode(String user, String secret) {
        String qrUrl = getQrUrl(user, secret);
        return QrCodeUtil.generate(qrUrl, 200, 200);
    }

    public static boolean checkCode(String secret, String code) throws NoSuchAlgorithmException, InvalidKeyException {
        Base32 codec = new Base32();
        byte[] decodeKey = codec.decode(secret);
        int window = 3;
        long time = System.currentTimeMillis() / 30000L;
        //time before or after window*30s code effect
        for (int i = -window; i <= window; i++) {
            long hash = verifyCode(decodeKey, time + i);
            if (StringUtil.equals(String.format("%06d", hash), code)) {
                return true;
            }
        }
        return false;
    }

    private static int verifyCode(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xf;
        long truncatedHash = 0;
        for (int i = 0; i < 4; i++) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String secret = generateSecret();
        System.out.println(secret);
        System.out.println(getQrUrl("1", secret));
        System.out.println(generateQrCode("1",  secret));
        System.out.println(checkCode("E7ZE4R24TNRTZIVQ3BBP3KA4", "465065"));
    }


}
