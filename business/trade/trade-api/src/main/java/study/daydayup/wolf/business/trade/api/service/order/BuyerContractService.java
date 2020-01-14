package study.daydayup.wolf.business.trade.api.service.order;

import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;

/**
 * study.daydayup.wolf.business.trade.api.service.tm
 *
 * @author Wingle
 * @since 2019/10/9 6:56 下午
 **/
public interface BuyerContractService {
    Result<Page<Contract>> find(Long buyerId);
}
