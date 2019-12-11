package study.daydayup.wolf.business.goods.biz.api;

import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.goods.api.service.LoanService;
import study.daydayup.wolf.framework.rpc.RpcService;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.biz.api
 *
 * @author Wingle
 * @since 2019/12/10 8:52 下午
 **/
@RpcService(protocol = "dubbo")
public class LoanServiceImpl implements LoanService {
    @Override
    public long create(Loan loan) {
        return 0;
    }

    @Override
    public int modify(Loan loan) {
        return 0;
    }

    @Override
    public List<Loan> findByOrgId(long orgId) {
        return null;
    }
}
