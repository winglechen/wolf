package study.daydayup.wolf.business.trade.order.biz.api;

import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.service.order.LoanContractService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;

/**
 * study.daydayup.wolf.business.trade.order.biz.api
 *
 * @author Wingle
 * @since 2020/1/14 12:43 下午
 **/
@RpcService
public class LoanContractServiceImpl implements LoanContractService {
    @Override
    public Result<Page<InstallmentTerm>> findDueInstallment() {
        return null;
    }

    @Override
    public Result<Page<InstallmentTerm>> findOverdueInstallment() {
        return null;
    }
}
