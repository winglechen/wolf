package study.daydayup.wolf.business.union.app.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PreviewResponse;
import study.daydayup.wolf.business.trade.api.service.buy.BuyService;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.union.app.controller
 *
 * @author Wingle
 * @since 2019/12/31 7:17 下午
 **/
@RestController
@RequestMapping("/api/v1")
public class LoanUnionController extends BaseUnionController {
    @Reference
    private BuyService buyService;
    @Reference
    private LoanService loanService;

    @PostMapping("/loan/preview")
    public Result<PreviewResponse> preview(@Validated @RequestBody BuyRequest request) {
        if (null == request || null == request.getGoods()) {
            throw new IllegalArgumentException("goods info can't be null");
        }

        Buyer buyer = new Buyer();
        buyer.setId(getFromSession("accountId", Long.class));
        buyer.setName(getFromSession("account", String.class));
        request.setBuyer(buyer);

        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        Long orgId = getFromSession("orgId", Long.class);

        for (GoodsRequest goods : request.getGoods()) {
            goods.setOrgId(orgId);
        }

        return buyService.preview(request);
    }

    @PostMapping("/loan/confirm")
    public Result<ConfirmResponse> confirm(@Validated @RequestBody BuyRequest request) {
        if (null == request || null == request.getGoods()) {
            throw new IllegalArgumentException("goods info can't be null");
        }

        Buyer buyer = new Buyer();
        buyer.setId(getFromSession("accountId", Long.class));
        buyer.setName(getFromSession("account", String.class));
        request.setBuyer(buyer);

        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        Long orgId = getFromSession("orgId", Long.class);

        for (GoodsRequest goods : request.getGoods()) {
            goods.setOrgId(orgId);
        }
        return buyService.confirm(request);
    }

    @GetMapping("/loan/{tradeNo}")
    public Result<Contract> detail(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = new TradeId();
        tradeId.setTradeNo(tradeNo);
        tradeId.setBuyerId(getFromSession("accountId", Long.class));
        tradeId.setSellerId(getFromSession("orgId", Long.class));

        return loanService.find(tradeId);
    }

    @GetMapping("/loan")
    public Result findByBuyer() {
        return null;
    }

    @PostMapping("/loan/repay")
    public Result repay() {
        return null;
    }

    @PostMapping("/loan/repay/delay")
    public Result delayRepay() {
        return null;
    }
}
