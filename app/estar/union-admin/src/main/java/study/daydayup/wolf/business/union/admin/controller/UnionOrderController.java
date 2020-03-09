package study.daydayup.wolf.business.union.admin.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.order.OrderOption;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.TypeRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.order.OrderRequest;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.api.service.order.SellerOrderService;
import study.daydayup.wolf.business.trade.api.service.tm.OrderManageService;
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
    private SellerOrderService sellerOrderService;
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

    @GetMapping("/loan/order")
    public Result<Page<Order>> findAll(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long sellerId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();
        return sellerOrderService.findAll(sellerId, pageRequest);
    }

    @GetMapping("/loan/order/loan")
    public Result<Page<Order>> loanList(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        TypeRequest request = initTypeRequest();
        request.setTradeType(TradeTypeEnum.LOAN_ORDER.getCode());

        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return sellerOrderService.findByTradeType(request, pageRequest);
    }

    @GetMapping("/loan/order/repay")
    public Result<Page<Order>> repayList(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        TypeRequest request = initTypeRequest();
        request.setTradeType(TradeTypeEnum.REPAY_ORDER.getCode());

        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return sellerOrderService.findByTradeType(request, pageRequest);
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

    private StateRequest initStateRequest() {
        return initStateRequest(null);
    }

    private StateRequest initStateRequest(StateRequest request) {
        if (request == null) {
            request = new StateRequest();
        }

        request.setOrderOption(initOrderOption());
        Long orgId = session.get("orgId", Long.class);
        request.setSellerId(orgId);

        return request;
    }


    private TypeRequest initTypeRequest() {
        return initTypeRequest(null);
    }

    private TypeRequest initTypeRequest(TypeRequest request) {
        if (request == null) {
            request = new TypeRequest();
        }

        request.setOrderOption(initOrderOption());
        Long orgId = session.get("orgId", Long.class);
        request.setSellerId(orgId);

        return request;
    }


}
