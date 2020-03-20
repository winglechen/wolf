package study.daydayup.wolf.middleware.notice.biz.sms.india.skyline;


import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.uc.biz.sms.india.skyline
 *
 * @author Wingle
 * @since 2020/3/19 7:41 下午
 **/
@Data
@Builder
public class SkylineSMS  implements Request {
    private String content;
    /**
     * 短信版本
     */
    private String version;

    /**
     * 发送者id
     */
    private String senderid;

    /***
     * 短信接收号码，多个号码之间以英文逗号分隔
     */
    private String numbers;
}
