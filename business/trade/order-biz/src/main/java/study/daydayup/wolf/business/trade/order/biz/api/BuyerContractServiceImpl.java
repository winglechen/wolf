package study.daydayup.wolf.business.trade.order.biz.api;

import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.service.order.BuyerContractService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;

/**
 * study.daydayup.wolf.business.trade.order.biz.api
 *
 * @author Wingle
 * @since 2020/1/14 12:40 下午
 **/
@RpcService(protocol = "dubbo")
public class BuyerContractServiceImpl implements BuyerContractService {
    @Override
    public Result<Page<Contract>> find(Long buyerId) {
        return null;
    }
}
