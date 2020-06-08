package study.daydayup.wolf.business.pay.biz.service.india.cashfree;

import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.cashfree
 *
 * @author Wingle
 * @since 2020/6/8 12:09 下午
 **/
public class SignUtil {
    private static final String[] keyArray = {
            "orderId", "orderAmount", "referenceId", "txStatus", "paymentMode",
            "txMsg", "txTime"
    };
    public static String create(String secretKey, Map<String, Object> data) {
        if (MapUtil.isEmpty(data) || StringUtil.isBlank(secretKey)) {
            return null;
        }

        try {
            Mac sha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec spec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            sha256.init(spec);

            String tokenString = formatData(data);
            byte[] tokenBytes  = sha256.doFinal(tokenString.getBytes());
            return Base64.getEncoder().encodeToString(tokenBytes);
        } catch (Exception e) {
            return null;
        }
    }

    private static String formatData(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (String key : keyArray) {
            sb.append(data.get(key));
        }

        return sb.toString();
    }
}
