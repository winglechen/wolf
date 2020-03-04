package study.daydayup.wolf.business.union.app.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResultResponse;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;

/**
 * study.daydayup.wolf.business.union.app.service
 *
 * @author Wingle
 * @since 2020/3/4 12:41 下午
 **/
@Service
public class UnionLoanService implements study.daydayup.wolf.framework.layer.domain.Service {
    @Reference
    private LoanService loanService;

    public PayResponse pay(PayRequest request) {
        return null;
    }

    public PayResultResponse payResult(TradeId tradeId) {
        return null;
    }
}
