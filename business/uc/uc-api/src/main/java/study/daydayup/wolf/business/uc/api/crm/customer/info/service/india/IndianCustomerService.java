package study.daydayup.wolf.business.uc.api.crm.customer.info.service.india;

import study.daydayup.wolf.business.uc.api.crm.customer.info.dto.india.IndianPayInfo;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.uc.api.crm.customer.info.service.india
 *
 * @author Wingle
 * @since 2020/3/22 9:43 下午
 **/
public interface IndianCustomerService extends Service {
    Result<IndianPayInfo> findIndianBankCard(Long accountId, Long orgId);
    Result<IndianPayInfo> findIndianAadhaar(Long accountId, Long orgId);
    Result<IndianPayInfo> findIndianContact(Long accountId, Long orgId);
}
