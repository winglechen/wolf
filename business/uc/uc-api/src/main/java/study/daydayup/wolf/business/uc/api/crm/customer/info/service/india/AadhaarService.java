package study.daydayup.wolf.business.uc.api.crm.customer.info.service.india;

import study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india.Aadhaar;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.uc.api.crm.customer.info.service.india
 *
 * @author Wingle
 * @since 2020/3/21 7:11 下午
 **/
public interface AadhaarService extends Service {
    Result<Aadhaar> find(Long accountId, Long orgId);
    Result<Integer> add(Aadhaar aadhaar);
    Result<Integer> modify(Aadhaar aadhaar);
}
