package study.daydayup.wolf.middleware.notice.biz.sms.india.skyline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.middleware.notice.api.config.SMSConfig;
import study.daydayup.wolf.middleware.notice.api.config.sms.Supplier;
import study.daydayup.wolf.middleware.notice.api.domain.exception.InvalidSMSConfigException;
import study.daydayup.wolf.middleware.notice.api.domain.exception.SMSEncodeFailException;
import study.daydayup.wolf.middleware.notice.api.domain.exception.SMSSendFailException;
import study.daydayup.wolf.middleware.notice.api.domain.exception.SMSTooLongException;
import study.daydayup.wolf.middleware.notice.biz.domain.service.sms.AbstractSender;
import study.daydayup.wolf.middleware.notice.biz.domain.service.sms.Sender;
import study.daydayup.wolf.common.util.encrypt.MD5Util;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
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
public class SkylineSender extends AbstractSender implements Sender {
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final int MAX_SMS_LENGTH = 1024;
    private static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static final String CONFIG_KEY = "skyline";
    private static final String API_VERSION = "1.0";

    @Resource
    private SMSConfig config;
    private Supplier skylineConfig;

    private String mobile;
    private String msg;

    @Override
    public int send(@NonNull String mobile, @NonNull String msg) {
        this.mobile = mobile;
        this.msg = msg;

        validConfig();
        return sendSms();
    }

    private void validConfig() {
        if (null == config.getSupplier()) {
            throw new InvalidSMSConfigException();
        }
        if (null == config.getSupplier().get(CONFIG_KEY)) {
            throw new InvalidSMSConfigException();
        }

        skylineConfig = config.getSupplier().get(CONFIG_KEY);
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
            JSONObject responseJson = JSON.parseObject(responseBody);

            if (0 == responseJson.getInteger("status")) {
                return 1;
            }
        } catch (Exception e) {
            log.warn("SkylineSender send sms fail", e);
            return 0;
        }

        return 0;
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
        DateTimeFormatter df =DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String now = df.format(LocalDateTime.now());

        String signStr = StringUtil.join(
                skylineConfig.getAppId(),
                skylineConfig.getAppSecret(),
                now
        );
        String sign = MD5Util.md5(signStr);

        return StringUtil.join(
                skylineConfig.getSendUrl(), "sendsmsV2?",
                "account=", skylineConfig.getAppId(),
                "&sign=", sign,
                "&datetime=", now
        );
    }

    private String createSmsContent() {
        try {
            String content = URLEncoder.encode(msg, StandardCharsets.UTF_8.toString());
            if (content.length() >= MAX_SMS_LENGTH ) {
                throw new SMSTooLongException();
            }

            SkylineSMS sms = SkylineSMS.builder()
                    .content(content)
                    .senderid(config.getSenderNum())
                    .version(API_VERSION)
                    .numbers(mobile)
                    .build();

            return JSON.toJSONString(sms);
        } catch (UnsupportedEncodingException e) {
            throw new SMSEncodeFailException();
        }
    }
}
