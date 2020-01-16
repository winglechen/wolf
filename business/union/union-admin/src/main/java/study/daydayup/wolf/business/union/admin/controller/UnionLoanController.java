package study.daydayup.wolf.business.union.admin.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import study.daydayup.wolf.business.trade.api.dto.tm.order.OrderRequest;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.api.service.tm.ContractManageService;
import study.daydayup.wolf.business.trade.api.service.tm.OrderManageService;
import study.daydayup.wolf.framework.layer.context.RpcContext;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.admin.controller
 *
 * @author Wingle
 * @since 2020/1/9 7:32 下午
 **/
@RestController
public class UnionLoanController implements Controller {
    @Reference
    private ContractService contractService;
    @Reference
    private OrderService orderService;
    @Reference
    private ContractManageService contractManageService;
    @Reference
    private OrderManageService orderManageService;
    @Resource
    private Session session;
    @Resource
    private RpcContext rpcContext;

    @GetMapping("/loan/contract/{tradeNo}")
    public Result<Contract> contractDetail(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = createTradeId(tradeNo);
        ContractOption option = initContractOption();

        return contractService.find(tradeId, option);
    }

    @GetMapping("/loan/order/{tradeNo}")
    public Result<Order> orderDetail(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = createTradeId(tradeNo);
        OrderOption option = initOrderOption();

        return orderService.find(tradeId, option);
    }

    @GetMapping("/loan/contract")
    public Result<Page<Contract>> contractList(ContractRequest request) {
        initContractRequest(request);
        return contractManageService.find(request);
    }

    @GetMapping("/loan/order")
    public Result<Page<Order>> orderList(OrderRequest request) {
        initOrderRequest(request);

        return orderManageService.find(request);
    }

    @GetMapping("/loan/contract/waitToApprove")
    public Result<Page<Contract>> waitToApproveList() {
        ContractRequest request = initContractRequest();
        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        request.setState(new WaitToApproveState().getCode());

        return contractList(request);
    }

    @GetMapping("/loan/contract/approved")
    public Result<Page<Contract>> approvedList() {
        ContractRequest request = initContractRequest();
        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        request.setState(new ApprovedState().getCode());

        return contractList(request);
    }

    @GetMapping("/loan/contract/due")
    public Result<Page<Contract>> dueList() {
        ContractRequest request = initContractRequest();
        //request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        //request.setRepayState(new DueState().getCode());
        request.setRepayType(InstallmentTypeEnum.DEFAULT.getCode());
        request.setRepayDueAt(rpcContext.getRequestTime());

        return contractList(request);
    }

    @GetMapping("/loan/contract/overdue")
    public Result<Page<Contract>> overdueList() {
        ContractRequest request = initContractRequest();
        //request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        //request.setRepayState(new OverdueState().getCode());
        request.setRepayType(InstallmentTypeEnum.DEFAULT.getCode());
        request.setRepayDueAt(rpcContext.getRequestTime());

        return contractList(request);
    }

    @GetMapping("/loan/order/loan")
    public Result<Page<Order>> loanList() {
        OrderRequest request = initOrderRequest();
        request.setTradeType(TradeTypeEnum.LOAN_ORDER.getCode());
        return orderList(request);
    }

    @GetMapping("/loan/order/repay")
    public Result<Page<Order>> repayList() {
        OrderRequest request = initOrderRequest();
        request.setTradeType(TradeTypeEnum.REPAY_ORDER.getCode());
        return orderList(request);
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

    private ContractOption initContractOption() {
        return ContractOption.builder()
                .withLoanTerm(true)
                .withInstallmentTerm(true)
                .build();
    }

    private OrderOption initOrderOption() {
        return OrderOption.builder()
                .withOrderLine(false)
                .withAddress(false)
                .build();
    }

    private ContractRequest initContractRequest() {
        return initContractRequest(null);
    }

    private ContractRequest initContractRequest(ContractRequest request) {
        if (request == null) {
            request = new ContractRequest();
        }

        request.setOption(initContractOption());

        Long orgId = session.get("orgId", Long.class);
        request.setSellerId(orgId);

        return request;
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
