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
import study.daydayup.wolf.business.trade.api.dto.buy.loan.LoanRequest;
import study.daydayup.wolf.business.trade.api.service.buy.BuyService;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.union.app.controller
 *
 * @author Wingle
 * @since 2019/12/31 7:17 下午
 **/
@RestController
public class LoanController extends BaseUnionController {
    @Reference
    private BuyService buyService;
    @Reference
    private LoanService loanService;

    @PostMapping("/loan/preview")
    public Result<PreviewResponse> preview(@Validated @RequestBody LoanRequest loanRequest) {
        BuyRequest request = initBuyRequest(loanRequest);

        Buyer buyer = new Buyer();
        buyer.setId(getFromSession("accountId", Long.class));
        buyer.setName(getFromSession("account", String.class));
        request.setBuyer(buyer);

        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        Long orgId = getFromSession("orgId", Long.class);

        for (GoodsRequest goods : request.getGoodsRequest()) {
            goods.setOrgId(orgId);
        }

        return buyService.preview(request);
    }


    @PostMapping("/loan/confirm")
    public Result<ConfirmResponse> confirm(@Validated @RequestBody LoanRequest loanRequest) {
        BuyRequest request = initBuyRequest(loanRequest);

        Buyer buyer = new Buyer();
        buyer.setId(getFromSession("accountId", Long.class));
        buyer.setName(getFromSession("account", String.class));
        request.setBuyer(buyer);

        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        Long orgId = getFromSession("orgId", Long.class);

        for (GoodsRequest goods : request.getGoodsRequest()) {
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
    public Result<Page<Contract>> findByBuyer() {
        return null;
    }

    @PostMapping("/loan/repay")
    public Result<Object> repay() {
        return null;
    }

    @PostMapping("/loan/repay/delay")
    public Result<Object> delayRepay() {
        return null;
    }

    private BuyRequest initBuyRequest(LoanRequest loanRequest) {
        BuyRequest request = new BuyRequest();

        GoodsRequest goodsRequest = new GoodsRequest();
        goodsRequest.setGoodsId(loanRequest.getGoodsId());
        List<GoodsRequest> goodsRequestList = new ArrayList<>();
        goodsRequestList.add(goodsRequest);
        request.setGoodsRequest(goodsRequestList);

        if (null != loanRequest.getTradeNo()) {
            request.setTradeNo(loanRequest.getTradeNo());
        }

        return request;
    }
}
