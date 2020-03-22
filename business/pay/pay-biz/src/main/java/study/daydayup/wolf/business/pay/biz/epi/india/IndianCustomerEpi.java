package study.daydayup.wolf.business.pay.biz.epi.india;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.crm.customer.info.service.india.IndianCustomerService;
import study.daydayup.wolf.framework.layer.epi.Epi;

/**
 * study.daydayup.wolf.business.pay.biz.epi.india
 *
 * @author Wingle
 * @since 2020/3/22 10:08 下午
 **/
@Component
public class IndianCustomerEpi implements Epi {
    @Reference
    private IndianCustomerService customerService;


}
