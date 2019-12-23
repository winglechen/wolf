package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.state.loan.WaitToApproveState;
import study.daydayup.wolf.business.trade.api.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeGoods;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeInstallment;
import study.daydayup.wolf.business.trade.api.vo.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.vo.contract.LoanTerm;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.common.node.AbstractTradeNode;
import study.daydayup.wolf.business.trade.buy.biz.epi.GoodsEpi;
import study.daydayup.wolf.common.model.type.id.TradeNo;
import study.daydayup.wolf.common.util.finance.Rate;
import study.daydayup.wolf.common.util.finance.pdl.PDLInterest;

import javax.annotation.Resource;
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
    private TradeGoods goods;
    private Contract contract;

    @Override
    public void run(BuyContext context) {
        init(context);

        createTradeNo();
        initSellerAndBuyer();
        initLoanTerm();
        initInstallmentTerm();
    }

    protected void init(BuyContext context) {
        this.context = context;
        goods = getGoodsList().get(0);

        contract = Contract.builder()
                .tradeType(context.getTradeType().getCode())
                .state(new WaitToApproveState().getCode())
                .source(context.getRequest().getSource())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private void createTradeNo() {
        String tradeNo = TradeNo.builder()
                .tradePhase(context.getTradePhase())
                .accountId(context.getBuyer().getId())
                .build()
                .toString();

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
        long fee = Rate.calculate(loanTerm.getAmount(), loanTerm.getHandlingFeeRate());
        loanTerm.setHandlingFee(fee);

        long interest = PDLInterest.rate(loanTerm.getAmount(), loanTerm.getInterestRate(), loanTerm.getPeriod());
        loanTerm.setInterest(interest);
    }

    private void initInstallmentTerm() {
        List<InstallmentTerm> terms = new ArrayList<>();
        List<TradeInstallment> installments = goods.getInstallmentList();

        for (int i = 0, len=installments.size(); i < len; i++) {
            TradeInstallment installment = installments.get(i);

            InstallmentTerm term = InstallmentTerm.builder()
                    .tradeNo(contract.getTradeNo())
                    .buyerId(contract.getBuyerId())
                    .sellerId(contract.getSellerId())
                    .installmentNo(i+1)
                    .period(installment.getPeriod())
                    .percentage(installment.getPercentage())
                    .feePercentage(installment.getFeePercentage())
                    .installmentType(installment.getInstallmentType())
                    .build();

            calculateInstallmentAmount(term);

            terms.add(term);
        }

        contract.setInstallmentTermList(terms);
    }

    private void calculateInstallmentAmount(InstallmentTerm term) {
        LoanTerm loan = contract.getLoanTerm();

        long amount = Rate.calculate(loan.getAmount(), term.getPercentage());
        term.setAmount(amount);

        long fee = Rate.calculate(loan.getHandlingFee(), term.getFeePercentage());
        term.setHandlingFee(fee);

        long interest = PDLInterest.rate(loan.getAmount(), loan.getInterestRate(), term.getPeriod());
        term.setInterest(interest);
    }

}
