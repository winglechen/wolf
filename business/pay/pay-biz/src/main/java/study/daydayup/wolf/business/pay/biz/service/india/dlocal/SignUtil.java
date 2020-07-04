package study.daydayup.wolf.business.pay.biz.service.india.dlocal;

import lombok.NonNull;
import study.daydayup.wolf.common.util.lang.CharsetUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.dlocal
 *
 * @author Wingle
 * @since 2020/7/4 2:07 下午
 **/
public class SignUtil {
    private static final String HMAC_ALGORITHM = "HmacSHA256";

    public static String create(String appSecret, String appName, String xDate, String body) {
        ByteArrayOutputStream bout = initStream(appName, xDate, body);
        if (bout == null) {
            return null;
        }

        byte[] sign = createSign(appSecret, bout);
        if (sign == null) {
            return null;
        }

        return formatSign(sign);
    }

    private static String formatSign(@NonNull byte[] sign) {
        Formatter formatter = new Formatter();
        for (byte b : sign) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    private static byte[] createSign(String appSecret, ByteArrayOutputStream bout) {
        SecretKeySpec keySpec = new SecretKeySpec(appSecret.getBytes(), HMAC_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(keySpec);
            return mac.doFinal(bout.toByteArray());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return null;
        }
    }

    private static ByteArrayOutputStream initStream(String appName, String xDate, String body) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            bout.write(appName.getBytes(CharsetUtil.UTF_8));
            bout.write(xDate.getBytes(CharsetUtil.UTF_8));
            bout.write(body.getBytes(CharsetUtil.UTF_8));
        } catch (IOException e) {
            return null;
        }

        return bout;
    }
}
