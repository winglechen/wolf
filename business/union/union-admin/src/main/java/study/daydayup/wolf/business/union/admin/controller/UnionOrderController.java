package study.daydayup.wolf.business.union.admin.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.goods.api.enums.InstallmentTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.ApprovedState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.WaitToApproveState;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.dto.order.OrderOption;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.ContractRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.order.OrderRequest;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.api.service.order.SellerContractService;
import study.daydayup.wolf.business.trade.api.service.tm.ContractManageService;
import study.daydayup.wolf.business.trade.api.service.tm.OrderManageService;
import study.daydayup.wolf.framework.layer.context.RpcContext;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.admin.controller
 *
 * @author Wingle
 * @since 2020/1/9 7:32 下午
 **/
@RestController
public class UnionOrderController implements Controller {
    @Reference
    private OrderService orderService;
    @Reference
    private OrderManageService orderManageService;
    @Resource
    private Session session;

    @GetMapping("/loan/order/{tradeNo}")
    public Result<Order> orderDetail(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = createTradeId(tradeNo);
        OrderOption option = initOrderOption();

        return orderService.find(tradeId, option);
    }

    @GetMapping("/loan/order/search")
    public Result<Page<Order>> orderSearch(OrderRequest request) {
        initOrderRequest(request);

        return orderManageService.find(request);
    }

    @GetMapping("/loan/order/loan")
    public Result<Page<Order>> loanList() {
        OrderRequest request = initOrderRequest();
        request.setTradeType(TradeTypeEnum.LOAN_ORDER.getCode());
        return orderSearch(request);
    }

    @GetMapping("/loan/order/repay")
    public Result<Page<Order>> repayList() {
        OrderRequest request = initOrderRequest();
        request.setTradeType(TradeTypeEnum.REPAY_ORDER.getCode());
        return orderSearch(request);
    }

    private TradeId createTradeId(String tradeNo) {
        if (tradeNo == null) {
            throw new IllegalArgumentException("tradeNo can't be null");
        }

        TradeId tradeId = new TradeId();
        tradeId.setTradeNo(tradeNo);

        Long orgId = session.get("orgId", Long.class);
        tradeId.setSellerId(orgId);

        return tradeId;
    }

    private OrderOption initOrderOption() {
        return OrderOption.builder()
                .withOrderLine(false)
                .withAddress(false)
                .build();
    }

    private OrderRequest initOrderRequest() {
       return initOrderRequest(null);
    }

    private OrderRequest initOrderRequest(OrderRequest request) {
        if (request == null) {
            request = new OrderRequest();
        }

        request.setOption(initOrderOption());

        Long orgId = session.get("orgId", Long.class);
        request.setSellerId(orgId);

        return request;
    }

}
