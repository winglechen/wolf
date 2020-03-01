package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.enums.InstallmentTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.WaitToApproveState;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Goods;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.base.node.AbstractTradeNode;
import study.daydayup.wolf.common.lang.enums.finance.FeeStrategyEnum;
import study.daydayup.wolf.common.lang.enums.unit.RateEnum;
import study.daydayup.wolf.common.model.type.id.TradeNo;
import study.daydayup.wolf.common.model.type.number.Rate;
import study.daydayup.wolf.common.util.finance.RateUtil;
import study.daydayup.wolf.common.util.finance.installment.RateInstallment;
import study.daydayup.wolf.common.util.finance.Interest;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.framework.layer.context.RpcContext;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.node
 *
 * @author Wingle
 * @since 2019/12/17 8:19 下午
 **/
@Component
public class CreateContractNode extends AbstractTradeNode implements TradeNode {
    private Goods goods;
    private Contract contract;

    @Resource
    private RpcContext rpcContext;

    @Override
    public void run(BuyContext context) {
        init(context);

        createTradeNo();
        initSellerAndBuyer();

        initGoods();
        initLoanTerm();
        initInstallmentTerm();

        context.setContract(contract);
    }

    @Override
    protected void init(BuyContext context) {
        this.context = context;

        contract = Contract.builder()
                .tradeType(context.getTradeType().getCode())
                .state(new WaitToApproveState())
                .source(context.getRequest().getSource())
                .createdAt(rpcContext.getRequestTime())
                .build();
    }

    private void createTradeNo() {
        BuyRequest request = context.getRequest();
        if (null != request.getTradeNo()) {
            contract.setTradeNo(request.getTradeNo());
            return;
        }

        String tradeNo = TradeNo.builder()
                .tradePhase(context.getTradePhase())
                .accountId(context.getBuyer().getId())
                .build()
                .create();
        contract.setTradeNo(tradeNo);
    }

    private void initSellerAndBuyer() {
        Buyer buyer = context.getBuyer();
        Seller seller = context.getSeller();

        contract.setBuyerId(buyer.getId());
        contract.setBuyerName(buyer.getName());
        contract.setSellerId(seller.getId());
        contract.setSellerName(seller.getName());
    }

    private void initGoods() {
        goods = getGoodsList().get(0);

        goods.setBuyerId(contract.getBuyerId());
        goods.setPayPrice(goods.getSalePrice());
        goods.setPostage(BigDecimal.ZERO);
    }

    private void initLoanTerm() {
        LoanTerm loanTerm = goods.getLoanTerm();
        loanTerm.setTradeNo(contract.getTradeNo());
        loanTerm.setBuyerId(contract.getBuyerId());
        loanTerm.setCreatedAt(contract.getCreatedAt());

        calculateLoanFeeAndInterest(loanTerm);
        contract.setLoanTerm(loanTerm);
    }

    private void initInstallmentTerm() {
        List<InstallmentTerm> terms = goods.getInstallmentTermList();
        int installmentCount = terms.size();
        if (installmentCount <= 0) {
            createInstallmentByLoan();
            return;
        }

        LoanTerm loan = contract.getLoanTerm();
        RateInstallment rateInstallment = new RateInstallment(loan.getAmount(), installmentCount);
        RateInstallment rateFee = new RateInstallment(loan.getHandlingFee(), installmentCount);

        for (InstallmentTerm term : terms) {
            term.setTradeNo(contract.getTradeNo());
            term.setBuyerId(contract.getBuyerId());

            calculateInstallmentInterest(term);
            term.setLoanAmount(rateInstallment.split(term.getPercentage(), RateEnum.PER_HUNDRED));
            setInstallmentFee(term, rateFee.split(term.getFeePercentage(), RateEnum.PER_HUNDRED));
            term.setPenalty(BigDecimal.ZERO);

            calculateInstallmentAmount(term);

        }

        contract.setInstallmentTermList(terms);
    }

    private void calculateLoanFeeAndInterest(LoanTerm loanTerm) {
        BigDecimal fee = RateUtil.calculate(loanTerm.getAmount(), loanTerm.getHandlingFeeRate());
        loanTerm.setHandlingFee(fee);

        BigDecimal interest = Interest.rate(loanTerm.getAmount(), loanTerm.getInterestRate(), loanTerm.getPeriod());
        loanTerm.setInterest(interest);
    }

    private void createInstallmentByLoan() {
        LoanTerm loan = contract.getLoanTerm();
        List<InstallmentTerm> terms = new ArrayList<>();

        //TODO installment state
        InstallmentTerm installmentTerm = InstallmentTerm.builder()
                .tradeNo(contract.getTradeNo())
                .buyerId(contract.getBuyerId())
                .sellerId(contract.getSellerId())
                .installmentNo(1)
                .installmentType(InstallmentTypeEnum.NO_INSTALLMENTS.getCode())

                .currency(loan.getCurrency())
                .loanAmount(loan.getAmount())
                .handlingFee(loan.getHandlingFee())
                .interest(loan.getInterest())
                .penalty(BigDecimal.ZERO)

                .period(loan.getPeriod())
                .percentage(Rate.HUNDRED_PERCENT)
                .feePercentage(Rate.HUNDRED_PERCENT)
                .build();

        calculateInstallmentAmount(installmentTerm);
        terms.add(installmentTerm);
        contract.setInstallmentTermList(terms);
    }

    private void calculateInstallmentAmount(@NonNull InstallmentTerm installment) {
        BigDecimal amount = DecimalUtil.add(
                installment.getLoanAmount(),
                installment.getHandlingFee(),
                installment.getInterest(),
                installment.getPenalty()
        );
        installment.setAmount(amount);
    }

    private void calculateInstallmentInterest(InstallmentTerm term) {
        LoanTerm loan = contract.getLoanTerm();

        BigDecimal interest = Interest.rate(loan.getAmount(), loan.getInterestRate(), term.getPeriod());
        term.setInterest(interest);
    }

    private void setInstallmentFee(InstallmentTerm term, BigDecimal fee) {
        LoanTerm loan = contract.getLoanTerm();
        int feeStrategy = loan.getFeePayStrategy();
        term.setHandlingFee(BigDecimal.ZERO);

        if (feeStrategy == FeeStrategyEnum.PRE.getCode()) {
            term.setHandlingFee(BigDecimal.ZERO);
            return;
        }

        if (feeStrategy == FeeStrategyEnum.POST.getCode()) {
            term.setHandlingFee(fee);
        }
    }

}
