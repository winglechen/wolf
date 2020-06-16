package study.daydayup.wolf.middleware.notice.biz.sms.india.skyline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;
import study.daydayup.wolf.middleware.notice.api.domain.exception.SMSEncodeFailException;
import study.daydayup.wolf.middleware.notice.api.domain.exception.SMSTooLongException;
import study.daydayup.wolf.middleware.notice.biz.domain.service.sms.AbstractSMSSender;
import study.daydayup.wolf.middleware.notice.biz.domain.service.sms.SMSSender;
import study.daydayup.wolf.common.util.encrypt.MD5Util;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * study.daydayup.wolf.business.uc.biz.sms.india.skyline
 *
 * @author Wingle
 * @since 2020/3/19 7:41 下午
 **/
@Component
@Slf4j
public class SkylineSender extends AbstractSMSSender implements SMSSender {
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final int MAX_SMS_LENGTH = 1024;
    private static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static final String CONFIG_KEY = "skyline";
    private static final String API_VERSION = "1.0";


    @Override
    public int send(@NonNull String mobile, @NonNull String msg, SMSSendConfig config) {
        this.mobile = mobile;
        this.msg = msg;
        this.smsSendConfig = config;

        initConfig(CONFIG_KEY);
        return sendSms();
    }

    private int sendSms() {
        Request request = createRequest();
        try {
            Response response = CLIENT.newCall(request).execute();
            return parseResponse(response);
        } catch (IOException e) {
            log.warn("SkylineSender send sms fail", e);
            return 0;
        }
    }

    private int parseResponse(Response response) {
        if (null == response || !response.isSuccessful()) {
            return 0;
        }

        try {
            String responseBody = Objects.requireNonNull(response.body()).string();
            log.info("skyline send to mobile({}) responseBody: {}", mobile, responseBody);
            JSONObject responseJson = JSON.parseObject(responseBody);

            if (0 == responseJson.getInteger("status")) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            log.warn("SkylineSender send sms fail", e);
            return 0;
        }
    }

    private Request createRequest() {
        String url = createSendUrl();
        String content = createSmsContent();
        RequestBody requestBody = RequestBody.create(content, JSON_CONTENT_TYPE);

        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    private String createSendUrl() {
        String now = getCurrentTimestamp();

        String signStr = StringUtil.join(
                supplierConfig.getAppId(),
                supplierConfig.getAppSecret(),
                now
        );
        String sign = MD5Util.md5(signStr);

        return StringUtil.join(
                supplierConfig.getSendUrl(), "sendsmsV2?",
                "account=", supplierConfig.getAppId(),
                "&sign=", sign,
                "&datetime=", now
        );
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter df =DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime now = LocalDateTime.now(zoneId);
        return df.format(now);
    }

    private String createSmsContent() {
        parseMsg();

        try {
            String content = URLEncoder.encode(msg, StandardCharsets.UTF_8.toString());
            if (content.length() >= MAX_SMS_LENGTH ) {
                throw new SMSTooLongException();
            }

            SkylineSMS sms = SkylineSMS.builder()
                    .content(content)
                    .senderid(smsConfig.getSenderNum())
                    .version(API_VERSION)
                    .numbers(mobile)
                    .build();

            return JSON.toJSONString(sms);
        } catch (UnsupportedEncodingException e) {
            throw new SMSEncodeFailException();
        }
    }
}
