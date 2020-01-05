package study.daydayup.wolf.business.union.app.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
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
public class LoanController extends BaseController {
    @Reference
    private BuyService buyService;
    @Reference
    private LoanService loanService;

    @PostMapping("/loan/preview")
    public Result<PreviewResponse> preview(@RequestBody BuyRequest request) {
        // validate
        return buyService.preview(request);
    }

    @PostMapping("/loan/confirm")
    public Result<ConfirmResponse> confirm(@RequestBody BuyRequest request) {
        // validate
        return buyService.confirm(request);
    }

    @GetMapping("/loan/{tradeNo}")
    public Result<Contract> detail(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = new TradeId();

        return loanService.find(tradeId);
    }

    @GetMapping("/loan")
    public Result findByOrgId() {
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
