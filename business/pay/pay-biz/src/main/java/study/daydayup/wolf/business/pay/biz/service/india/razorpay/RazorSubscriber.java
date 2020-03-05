package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/2/29 11:41 下午
 **/
@Component
@Slf4j
public class RazorSubscriber {
    @Resource
    private RazorConfig config;

    public String subscribe(String data) {
        log.info("RazorSubscriber: {}", data);
        return "RazorSubscriber get result";
    }
}
