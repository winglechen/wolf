package study.daydayup.wolf.middleware.notice.biz.sms.india.nxcloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.middleware.notice.api.config.SMSConfig;
import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;
import study.daydayup.wolf.middleware.notice.biz.domain.service.sms.AbstractSMSSender;
import study.daydayup.wolf.middleware.notice.biz.domain.service.sms.SMSSender;

import java.io.IOException;
import java.util.Objects;


/**
 * study.daydayup.wolf.middleware.notice.biz.sms.india.nxcloud
 *
 * @author Wingle
 * @since 2020/6/8 5:00 下午
 **/
@Component
@Slf4j
public class NxcloudSMSSender extends AbstractSMSSender implements SMSSender {
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");
    private static final String CONFIG_KEY = "nxcloud";


    @Override
    public int send(@NonNull String mobile, @NonNull String msg, SMSSendConfig config) {
        this.mobile = mobile;
        this.msg = msg;
        this.smsSendConfig = config;

        validConfig(CONFIG_KEY);
        return sendSms();
    }

    private int sendSms() {
        Request request = createRequest();
        try {
            Response response = CLIENT.newCall(request).execute();
            return parseResponse(response);
        } catch (IOException e) {
            log.warn("NxcloudSMSSender send sms fail", e);
            return 0;
        }
    }

    private int parseResponse(Response response) {
        try {
            String responseBody = Objects.requireNonNull(response.body()).string();
            log.info("nxcloud send to mobile({}) responseBody: {}", mobile, responseBody);
            JSONObject responseJson = JSON.parseObject(responseBody);

            if ("0".equalsIgnoreCase(responseJson.getString("code"))) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            log.warn("NxcloudSMSSender send sms fail", e);
            return 0;
        }
    }

    private Request createRequest() {
        msg = msg.replace("for " + SMSConfig.BRAND_PLACEHOLDER, "");

        RequestBody body = new FormBody.Builder()
                .add("appkey", supplierConfig.getAppId())
                .add("secretkey", supplierConfig.getAppSecret())
                .add("phone", mobile)
                .add("content", msg)
                .build();

        return new Request.Builder()
                .url(supplierConfig.getSendUrl())
                .post(body)
                .build();
    }

}
