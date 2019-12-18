package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.trade.api.dto.buy.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.exception.buy.GoodsNotFoundException;
import study.daydayup.wolf.business.trade.api.state.loan.WaitToApproveState;
import study.daydayup.wolf.business.trade.api.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeGoods;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeInstallment;
import study.daydayup.wolf.business.trade.api.vo.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.vo.contract.RepaymentTerm;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.common.node.AbstractTradeNode;
import study.daydayup.wolf.business.trade.buy.biz.epi.GoodsEpi;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.node
 *
 * @author Wingle
 * @since 2019/12/17 8:19 下午
 **/
@Component
public class CreateContractNode extends AbstractTradeNode implements TradeNode {
    @Resource
    private GoodsEpi goodsEpi;

    private BuyContext context;
    private TradeGoods goods;
    private Contract contract;

    @Override
    public void run(BuyContext context) {
        init(context);

        createTradeNo();
        initSellerAndBuyer();


    }

    private void init(BuyContext context) {
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
        contract.setTradeNo("1234567890");
    }

    private void initSellerAndBuyer() {
        Buyer buyer = context.getBuyer();
        Seller seller = context.getSeller();

        contract.setBuyerId(buyer.getId());
        contract.setBuyerName(buyer.getName());
        contract.setSellerId(seller.getId());
        contract.setSellerName(seller.getName());
    }

    private void initRepaymentTerm() {
        RepaymentTerm repaymentTerm = RepaymentTerm.builder()
                .tradeNo(contract.getTradeNo())
                .buyerId(contract.getBuyerId())
                .sellerId(contract.getSellerId())
                .amount(goods.getPayPrice())
                .currency(goods.getCurrency())
                .build();

        BeanUtils.copyProperties(goods.getLoan(), repaymentTerm);

        contract.setRepaymentTerm(repaymentTerm);
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
                    .duration(installment.getDuration())
                    .percentage(installment.getPercentage())
                    .feePercentage(installment.getFeePercentage())
                    .installmentType(installment.getInstallmentType())
                    .build();

            computeInstallmentAmount(term);

            terms.add(term);
        }

        contract.setInstallmentTermList(terms);
    }

    private void computeInstallmentAmount(InstallmentTerm term) {
        long amount     = contract.getRepaymentTerm().getAmount();
        int  feeRate    = contract.getRepaymentTerm().getHandlingFeeRate();
        long fee        = Math.round( amount * feeRate / 1000000);
    }

}
