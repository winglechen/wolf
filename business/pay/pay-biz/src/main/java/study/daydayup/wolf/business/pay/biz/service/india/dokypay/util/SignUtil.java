package study.daydayup.wolf.business.pay.biz.service.india.dokypay.util;

import lombok.NonNull;
import study.daydayup.wolf.business.pay.api.domain.exception.CreateSignFailException;
import study.daydayup.wolf.common.util.encrypt.ShaEncrypt;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.dokypay.util
 *
 * @author Wingle
 * @since 2020/4/28 7:28 下午
 **/
public class SignUtil {
    private static final String KEY_SIGN = "sign";
    private static final String KEY_EXT = "extInfo";

    public static String create(@NonNull String appSecret, @NonNull Map<String, Object> map) {
        if (map.isEmpty()) {
            return null;
        }

        Object[] keys = map.keySet().toArray();
        Arrays.sort(keys);

        StringBuilder originStr = new StringBuilder();
        Object value;
        for (Object key: keys) {
            value = map.get(key.toString());

            if (KEY_EXT.equals(key.toString())){
                value = parseExtInfo(value);
            }

            if (isIgnoredItem(key, value)) {
                continue;
            }

            originStr.append(key).append("=").append(value).append("&");
        }
        originStr.append("key=").append(appSecret);
        try {
            return ShaEncrypt.sha256(originStr.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new CreateSignFailException();
        }
    }

    private static boolean isIgnoredItem(Object key, Object value) {
        String sKey, sValue;
        sKey = key.toString().trim();

        if (KEY_SIGN.equals(sKey)) {
            return true;
        }

        if (null == value) {
            return true;
        }

        sValue = value.toString().trim();
        return StringUtil.isBlank(sValue);
    }

    private static String parseExtInfo(Object ext) {
        if ( !(ext instanceof Map)) {
            return null;
        }

        Map<String, Object> extInfo = (Map<String, Object>) ext;
        Object[] keys = extInfo.keySet().toArray();
        Arrays.sort(keys);

        StringBuilder extStr = new StringBuilder();
        Object value;
        boolean isFirst = true;
        for (Object key : keys) {
            value = extInfo.get(key.toString());
            if (null == value || StringUtil.isBlank(value.toString())) {
                continue;
            }

            if (!isFirst) {
                extStr.append("&");
            }
            extStr.append(key).append("=").append(value.toString());
            isFirst = false;
        }

        return extStr.toString();
    }
}
