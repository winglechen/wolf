package study.daydayup.wolf.business.trade.order.biz.domain.entity;

import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.LoanedState;
import study.daydayup.wolf.business.trade.api.domain.util.StateUtil;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

/**
 * study.daydayup.wolf.business.trade.order.domain.entity
 *
 * @author Wingle
 * @since 2019/10/7 11:54 下午
 **/
public class ContractEntity extends AbstractEntity<Contract> implements Entity  {
    private ContractOption option;

    public ContractEntity(@NonNull Contract contract, ContractOption option) {
        this.model = contract;

        if (option != null) {
            this.option = option;
        } else {
            this.option = new ContractOption();
        }
    }

    public void init() {
        formatLoanContract();
    }

    private void formatLoanContract() {
        int loanTradeType = TradeTypeEnum.LOAN_CONTRACT.getCode();
        if (!BeanUtil.equals(loanTradeType, model.getTradeType())) {
            return;
        }

        if (!StateUtil.equals(model.getState(), new LoanedState())) {
            return;
        }

        initRepaymentTerm();
        formatInstallmentTerm();
    }

    private void initRepaymentTerm() {

    }

    private void formatInstallmentTerm() {

    }


}
