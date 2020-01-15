package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.enums.InstallmentTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.WaitToApproveState;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Goods;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Installment;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Override
    public void run(BuyContext context) {
        init(context);

        createTradeNo();
        initSellerAndBuyer();
        initLoanTerm();
        initInstallmentTerm();

        context.setContract(contract);
    }

    @Override
    protected void init(BuyContext context) {
        this.context = context;
        goods = getGoodsList().get(0);

        contract = Contract.builder()
                .tradeType(context.getTradeType().getCode())
                .state(new WaitToApproveState())
                .source(context.getRequest().getSource())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private void createTradeNo() {
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

    private void initLoanTerm() {
        LoanTerm loanTerm = LoanTerm.builder()
                .tradeNo(contract.getTradeNo())
                .buyerId(contract.getBuyerId())
                .sellerId(contract.getSellerId())
                .amount(goods.getPayPrice())
                .currency(goods.getCurrency())
                .build();

        BeanUtils.copyProperties(goods.getLoan(), loanTerm);
        calculateLoanAmount(loanTerm);

        contract.setLoanTerm(loanTerm);
    }

    private void calculateLoanAmount(LoanTerm loanTerm) {
        BigDecimal fee = RateUtil.calculate(loanTerm.getAmount(), loanTerm.getHandlingFeeRate());
        loanTerm.setHandlingFee(fee);

        BigDecimal interest = Interest.rate(loanTerm.getAmount(), loanTerm.getInterestRate(), loanTerm.getPeriod());
        loanTerm.setInterest(interest);
    }

    private void createInstallmentByLoan() {
        LoanTerm loan = contract.getLoanTerm();
        List<InstallmentTerm> terms = new ArrayList<>();

        InstallmentTerm installmentTerm = InstallmentTerm.builder()
                .tradeNo(contract.getTradeNo())
                .buyerId(contract.getBuyerId())
                .sellerId(contract.getSellerId())
                .installmentNo(1)
                .period(loan.getPeriod())
                .percentage(Rate.HUNDRED_PERCENT)
                .feePercentage(Rate.HUNDRED_PERCENT)
                .installmentType(InstallmentTypeEnum.NO_INSTALLMENTS.getCode())
                .amount(loan.getAmount())
                .interest(loan.getInterest())
                .handlingFee(loan.getHandlingFee())
                .build();

        terms.add(installmentTerm);
        contract.setInstallmentTermList(terms);
    }

    private void initInstallmentTerm() {
        List<Installment> installments = goods.getInstallmentList();
        int installmentCount = installments.size();
        if (installmentCount <= 0) {
            createInstallmentByLoan();
            return;
        }

        LoanTerm loan = contract.getLoanTerm();
        List<InstallmentTerm> terms = new ArrayList<>();

        RateInstallment rateInstallment = new RateInstallment(loan.getAmount(), installmentCount);
        RateInstallment rateFee = new RateInstallment(loan.getHandlingFee(), installmentCount);

        for (int i = 0; i < installmentCount; i++) {
            Installment installment = installments.get(i);

            InstallmentTerm term = buildInstallmentTerm(installment, i);
            calculateInstallmentInterest(term);
            term.setAmount(rateInstallment.split(term.getPercentage(), RateEnum.PER_HUNDRED));
            setInstallmentFee(term, rateFee.split(term.getFeePercentage(), RateEnum.PER_HUNDRED));

            terms.add(term);
        }

        contract.setInstallmentTermList(terms);
    }

    private InstallmentTerm buildInstallmentTerm(Installment installment, int i) {
        return InstallmentTerm.builder()
                .tradeNo(contract.getTradeNo())
                .buyerId(contract.getBuyerId())
                .sellerId(contract.getSellerId())
                .installmentNo(i+1)
                .period(installment.getPeriod())
                .percentage(installment.getPercentage())
                .feePercentage(installment.getFeePercentage())
                .installmentType(installment.getInstallmentType())
                .build();
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
