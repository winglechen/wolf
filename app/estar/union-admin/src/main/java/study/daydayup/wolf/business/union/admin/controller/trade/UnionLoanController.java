package study.daydayup.wolf.business.union.admin.controller.trade;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.goods.api.enums.InstallmentTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.ApprovedState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.WaitToApproveState;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.ContractRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.InstallmentStateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;
import study.daydayup.wolf.business.trade.api.service.order.SellerContractService;
import study.daydayup.wolf.business.trade.api.service.order.SellerOrderService;
import study.daydayup.wolf.business.trade.api.service.tm.ContractManageService;
import study.daydayup.wolf.business.union.admin.dto.LoanWithOrder;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.context.RpcContext;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    private LoanService loanService;
    @Reference
    private ContractManageService contractManageService;
    @Reference
    private SellerContractService sellerContractService;
    @Reference
    private SellerOrderService sellerOrderService;
    @Resource
    private Session session;
    @Resource
    private RpcContext rpcContext;

    @GetMapping("/loan/contract/{tradeNo}")
    public Result<LoanWithOrder> contractDetail(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = initTradeId(tradeNo);
        ContractOption option = initContractOption();

        Contract contract = contractService.find(tradeId, option).notNullData();
        LoanWithOrder result = new LoanWithOrder();
        result.setContract(contract);

        List<Order> orderList = sellerOrderService.findByRelatedTradeNo(contract.getTradeNo(), contract.getSellerId()).getData();
        mergeLoanAndOrder(result, orderList);

        return Result.ok(result);
    }

    private void mergeLoanAndOrder(LoanWithOrder result, List<Order> orderList) {
        if (CollectionUtil.isEmpty(orderList)) {
            return ;
        }

        Map<Integer, List<Order>> tradeTypeMap = CollectionUtil.group(orderList, Order::getTradeType);
        result.setLoanOrderList(tradeTypeMap.get(TradeTypeEnum.LOAN_ORDER.getCode()));
        result.setRepayOrderList(tradeTypeMap.get(TradeTypeEnum.REPAY_ORDER.getCode()));
    }

    @GetMapping("/loan/contract/search")
    public Result<Page<Contract>> contractSearch(ContractRequest request) {
        initContractRequest(request);
        return contractManageService.find(request);
    }

    @GetMapping("/loan/contract")
    public Result<Page<Contract>> findBySeller(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long sellerId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();
        return sellerContractService.findAll(sellerId, pageRequest);
    }

    @GetMapping("/loan/contract/buyer")
    public Result<Page<Contract>> findByBuyer(@RequestParam("buyerId") Long buyerId, @RequestParam(value = "pageNum", required = false) Integer pageNum) {
        BuyerRequest request = initBuyerRequest(buyerId);

        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return sellerContractService.findByBuyerId(request, pageRequest);
    }

    @PutMapping("/loan/contract/approve/{tradeNo}")
    public Result<Object> approve(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = initTradeId(tradeNo);
        loanService.approve(tradeId);

        return Result.ok();
    }

    @PutMapping("/loan/contract/refuse/{tradeNo}")
    public Result<Object> refuse(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = initTradeId(tradeNo);
        loanService.refuse(tradeId);

        return Result.ok();
    }

    @PutMapping("/loan/contract/loan/{tradeNo}")
    public Result<String> loaning(@PathVariable String tradeNo) {
        TradeId tradeId = initTradeId(tradeNo);

        loanService.startLoan(tradeId);
        return Result.ok("ok");
    }

    @GetMapping("/loan/contract/waitToApprove")
    public Result<Page<Contract>> waitToApproveList(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        StateRequest request = initStateRequest();
        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        request.setState(new WaitToApproveState().getCode());

        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return sellerContractService.findByTradeState(request, pageRequest);
    }

    @GetMapping("/loan/contract/approved")
    public Result<Page<Contract>> approvedList(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        StateRequest request = initStateRequest();
        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        request.setState(new ApprovedState().getCode());

        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return sellerContractService.findByTradeState(request, pageRequest);
    }

    @GetMapping("/loan/contract/due")
    public Result<Page<Contract>> dueList(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        InstallmentStateRequest request = initInstallmentStateRequest();
        request.setDueAt(rpcContext.getRequestDate());
        //request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        //request.setRepayState(new DueState().getCode());
        request.setInstallmentType(InstallmentTypeEnum.DEFAULT.getCode());

        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return sellerContractService.findByInstallmentState(request, pageRequest);
    }

    @GetMapping("/loan/contract/overdue")
    public Result<Page<Contract>> overdueList(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        LocalDate yesterday = LocalDate.now().plusDays(-1);

        InstallmentStateRequest request = initInstallmentStateRequest();
        request.setDueAt(yesterday);
        request.setInstallmentType(InstallmentTypeEnum.DEFAULT.getCode());

        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return sellerContractService.findByInstallmentState(request, pageRequest);
    }

    @GetMapping("/loan/contract/overdue/{buyerId}")
    public Result<Page<Contract>> overdueList(@PathVariable("buyerId") Long buyerId, @RequestParam(value = "pageNum", required = false) Integer pageNum) {
        LocalDate yesterday = LocalDate.now().plusDays(-1);

        InstallmentStateRequest request = initInstallmentStateRequest();
        request.setDueAt(yesterday);
        request.setInstallmentType(InstallmentTypeEnum.DEFAULT.getCode());
        request.setBuyerId(buyerId);

        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return sellerContractService.findOverdueContractByBuyer(request, pageRequest);
    }

    private TradeId initTradeId(String tradeNo) {
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

    private StateRequest initStateRequest() {
        return initStateRequest(null);
    }

    private StateRequest initStateRequest(StateRequest request) {
        if (request == null) {
            request = new StateRequest();
        }

        request.setContractOption(initContractOption());
        Long orgId = session.get("orgId", Long.class);
        request.setSellerId(orgId);

        return request;
    }

    private BuyerRequest initBuyerRequest(Long buyerId) {
        BuyerRequest request = new BuyerRequest();

        request.setContractOption(initContractOption());
        Long orgId = session.get("orgId", Long.class);
        request.setSellerId(orgId);
        request.setBuyerId(buyerId);

        return request;
    }



    private InstallmentStateRequest initInstallmentStateRequest() {
        InstallmentStateRequest request = new InstallmentStateRequest();

        request.setContractOption(initContractOption());
        Long orgId = session.get("orgId", Long.class);
        request.setSellerId(orgId);

        return request;
    }
}
