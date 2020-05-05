package study.daydayup.wolf.business.union.app.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum;
import study.daydayup.wolf.business.goods.api.service.LoanGoodsService;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.exception.order.OrderCreateFailException;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.WaitToAuditState;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Goods;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.BuyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResultResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.loan.LoanRequest;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.service.buy.BuyService;
import study.daydayup.wolf.business.trade.api.service.order.BuyerContractService;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;
import study.daydayup.wolf.business.trade.api.service.order.SellerContractService;
import study.daydayup.wolf.business.union.app.dto.LoanAuditResponse;
import study.daydayup.wolf.business.union.app.service.UnionLoanService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.union.app.controller
 *
 * @author Wingle
 * @since 2019/12/31 7:17 下午
 **/
@RestController
public class UnionLoanController extends BaseUnionController {
    @Reference
    private BuyService buyService;
    @Reference
    private ContractService contractService;
    @Reference
    private BuyerContractService buyerContractService;
    @Reference
    private SellerContractService sellerContractService;
    @Resource
    private UnionLoanService unionLoanService;
    @Reference
    private LoanGoodsService loanGoodsService;
    @Resource
    private Session session;

    @PostMapping("/loan/audit/preview/{goodsId}")
    public Result<LoanAuditResponse> auditPreview(@PathVariable("goodsId") Long goodsId) {
        // preview loan
        BuyResponse loanResponse = loanPreview(goodsId);

        // find audit goods
        LoanGoods goods = findAuditGoods();

        // merge result
        LoanAuditResponse response = new LoanAuditResponse();
        response.setContract(loanResponse.getContract());
        response.setGoods(goods);

        return Result.ok(response);
    }


    @PutMapping("/loan/audit/pay")
    public Result<PayResponse> auditPay(@RequestBody PayRequest payRequest) {
        // find audit goods
        LoanGoods loan = findAuditGoods();
        Goods goods = loanToGoods(loan);
        if (goods == null) {
            return Result.fail(500, "audit goods not found");
        }

        // create audit fee order
        BuyRequest request = new BuyRequest();
        Buyer buyer = new Buyer();
        buyer.setId(getFromSession("accountId", Long.class));
        buyer.setName(getFromSession("account", String.class));
        request.setBuyer(buyer);

        Seller seller = new Seller();
        seller.setId(session.get("orgId", Long.class));
        request.setSeller(seller);

        request.setTradeType(TradeTypeEnum.AUDIT_FEE.getCode());
        request.setGoods(goods);
        request.setStoreTrade(true);

        request.setRelatedTradeNo(payRequest.getTradeNo());

        BuyResponse response = buyService.preview(request).notNullData();
        if (null == response.getOrder()) {
            throw new OrderCreateFailException();
        }

        // get pay args
        PayResponse payResponse = unionLoanService.audit(response.getOrder(), payRequest.getPaymentMethod());
        return Result.ok(payResponse);
    }

    @PostMapping("/loan/preview")
    public Result<BuyResponse> preview(@Validated @RequestBody LoanRequest loanRequest) {
        BuyRequest request = initBuyRequest(loanRequest);

        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        Long orgId = getFromSession("orgId", Long.class);

        if (null != loanRequest.getStoreOnPreview() && loanRequest.getStoreOnPreview()) {
            request.setStoreTrade(true);
        }

        if (null != loanRequest.getNeedAuditFee() && loanRequest.getNeedAuditFee()) {
            request.setTradeState(new WaitToAuditState());
        }

        for (GoodsRequest goods : request.getGoodsRequest()) {
            goods.setOrgId(orgId);
        }

        return buyService.preview(request);
    }


    @PostMapping("/loan/confirm")
    public Result<BuyResponse> confirm(@Validated @RequestBody LoanRequest loanRequest) {
        BuyRequest request = initBuyRequest(loanRequest);

        request.setTradeType(TradeTypeEnum.LOAN_CONTRACT.getCode());
        Long orgId = getFromSession("orgId", Long.class);

        for (GoodsRequest goods : request.getGoodsRequest()) {
            goods.setOrgId(orgId);
        }

        request.setStoreTrade(true);
        return buyService.confirm(request);
    }

    @GetMapping("/loan/contract/detail/{tradeNo}")
    public Result<Contract> detail(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = new TradeId();
        tradeId.setTradeNo(tradeNo);
        tradeId.setBuyerId(getFromSession("accountId", Long.class));
        tradeId.setSellerId(getFromSession("orgId", Long.class));

        ContractOption option = initContractOption();

        return contractService.find(tradeId, option);
    }

    @GetMapping("/loan/living")
    public Result<Contract> livingContract() {
        Long buyerId = session.get("accountId", Long.class);
        Long sellerId = session.get("sellerId", Long.class);

        TradeOwner owner = new TradeOwner();
        owner.setBuyerId(buyerId);
        owner.setSellerId( sellerId);
        ContractOption option = initContractOption();

        return buyerContractService.findLatest(owner, option);
    }

    @GetMapping("/loan")
    public Result<Page<Contract>> findByBuyer(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        BuyerRequest request = initBuyerRequest();
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return sellerContractService.findByBuyerId(request, pageRequest);
    }

    @GetMapping("/loan/repay/result/{tradeNo}")
    public Result<PayResultResponse> repayResult(@PathVariable("tradeNo") String tradeNo) {
        TradeId tradeId = initTradeId(tradeNo);

        PayResultResponse response = unionLoanService.payResult(tradeId);
        if (response == null) {
            return Result.fail(10000, "repay fail");
        }
        return Result.ok(response);
    }

    @PutMapping("/loan/repay")
    public Result<PayResponse> repay(@Validated @RequestBody PayRequest request) {
        String tradeNo = request.getTradeNo();
        TradeId tradeId = initTradeId(tradeNo);
        request.setTradeId(tradeId);

        PayResponse response = unionLoanService.pay(request, request.getPaymentMethod());
        if (response == null) {
            return Result.fail(10000, "repay fail");
        }
        return Result.ok(response);
    }

    @PutMapping("/loan/repay/installment")
    public Result<PayResponse> repayInstallment(@Validated @RequestBody PayRequest request) {
        if (null == request.getInstallmentNo()) {
            throw new IllegalArgumentException("InstallmentNo can't be null");
        }
        return repay(request);
    }

    @PutMapping("/loan/repay/delay")
    public Result<Object> delayRepay() {
        return null;
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

    private BuyerRequest initBuyerRequest() {
        BuyerRequest request = new BuyerRequest();

        request.setContractOption(initContractOption());
        Long orgId = session.get("orgId", Long.class);
        Long buyerId = session.get("accountId", Long.class);
        request.setSellerId(orgId);
        request.setBuyerId(buyerId);

        return request;
    }

    private ContractOption initContractOption() {
        return ContractOption.builder()
                .withLoanTerm(true)
                .withInstallmentTerm(true)
                .calculateRepayment(true)
                .build();
    }

    private BuyRequest initBuyRequest(LoanRequest loanRequest) {
        BuyRequest request = new BuyRequest();

        Buyer buyer = new Buyer();
        buyer.setId(getFromSession("accountId", Long.class));
        buyer.setName(getFromSession("account", String.class));
        request.setBuyer(buyer);

        Seller seller = new Seller();
        seller.setId(session.get("orgId", Long.class));
        request.setSeller(seller);

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

    private BuyResponse loanPreview(Long goodsId) {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setGoodsId(goodsId);
        loanRequest.setStoreOnPreview(true);
        loanRequest.setNeedAuditFee(true);

        return preview(loanRequest).notNullData();
    }

    private LoanGoods findAuditGoods() {
        Long orgId = getFromSession("orgId", Long.class);
        Integer goodsType = GoodsTypeEnum.AUDIT_FEE.getCode();
        return loanGoodsService.findOneByOrgId(orgId, goodsType);
    }

    private Goods loanToGoods(LoanGoods loan) {
        if (loan == null) {
            return null;
        }

        Goods goods = Goods.builder()
                .goodsId(loan.getId())
                .buyerId(session.get("accountId", Long.class))
                .sellId(loan.getOrgId())
                .build();

        BeanUtils.copyProperties(loan, goods);
        goods.setSalePrice(loan.getPrice());
        goods.setPayPrice(loan.getPrice());
        goods.setPostage(BigDecimal.ZERO);

        return goods;
    }

}
