package study.daydayup.wolf.business.uc.api.notice.sms.india.skyline;

import lombok.Data;

import java.io.Serializable;

/**
 * study.daydayup.wolf.business.uc.api.notice.sms.india.skyline
 *
 * @author Wingle
 * @since 2020/3/19 7:31 下午
 **/
@Data
public class SkylineConfig implements Serializable {
    private String appId = "cs_3wegkt";
    private String appSecret = "dwhg27jN";

    private String senderId = "CASHCR";
    private String senderUrl = "http://sms.skylinelabs.cc:20003/";
}
