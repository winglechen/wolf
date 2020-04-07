package study.daydayup.wolf.business.union.app.controller.dev;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;
import study.daydayup.wolf.business.union.app.controller.BaseUnionController;
import study.daydayup.wolf.business.union.app.dto.LoanActionRequest;
import study.daydayup.wolf.business.union.app.service.UnionPayoutService;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * study.daydayup.wolf.business.union.app.controller
 * JUST FOR DEV
 * @author Wingle
 * @since 2019/12/31 7:17 下午
 **/
@RestController
public class UnionDevController extends BaseUnionController {
    @Reference
    private LoanService loanService;
    @Resource
    private UnionPayoutService payoutService;



    @PutMapping("/loan/dev/approve/{tradeNo}")
    public Result<String> approve(@PathVariable String tradeNo) {
        TradeId tradeId = initTradeId(tradeNo);

        loanService.approve(tradeId);
        return Result.ok("ok");
    }

    @PutMapping("/loan/dev/loaning/{tradeNo}")
    public Result<String> loaning(@PathVariable String tradeNo) {
        TradeId tradeId = initTradeId(tradeNo);

        Order order = loanService.startLoan(tradeId).notNullData();
        payoutService.payout(order);
        return Result.ok("ok");
    }

    @PutMapping("/loan/dev/loaned")
    public Result<String> completeLoan(@Validated @RequestBody LoanActionRequest request) {
        TradeId tradeId = initTradeId(request.getTradeNo());
        LocalDate effectAt = request.getEffectAt();

        loanService.completeLoan(tradeId, effectAt);
        return Result.ok("ok");
    }

    private TradeId initTradeId(String tradeNo) {
        if (tradeNo == null) {
            throw new IllegalArgumentException("tradeNo can't be null");
        }

        TradeId tradeId = new TradeId();
        tradeId.setTradeNo(tradeNo);

        Long orgId = session.get("orgId", Long.class);
        Long accountId = session.get("accountId", Long.class);
        tradeId.setSellerId(orgId);
        tradeId.setBuyerId(accountId);

        return tradeId;
    }

    private ContractOption initContractOption() {
        return ContractOption.builder()
                .withLoanTerm(true)
                .withInstallmentTerm(true)
                .calculateRepayment(true)
                .build();
    }


}
