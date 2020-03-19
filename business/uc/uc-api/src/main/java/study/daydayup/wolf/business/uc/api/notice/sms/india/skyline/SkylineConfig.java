package study.daydayup.wolf.business.uc.api.notice.sms.india.skyline;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * study.daydayup.wolf.business.uc.api.notice.sms.india.skyline
 *
 * @author Wingle
 * @since 2020/3/19 7:31 下午
 **/
@Data
@Configuration
//@ConfigurationProperties("wolf.sms.india.skyline")
public class SkylineConfig implements Serializable {
    private String baseUrl = "http://sms.skylinelabs.cc:20003/";
    private String account = "cs_3wegkt";
    private String password = "dwhg27jN";
    private String senderId = "CASHCR";

}
