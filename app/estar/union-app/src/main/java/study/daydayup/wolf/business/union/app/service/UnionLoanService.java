package study.daydayup.wolf.business.union.app.service;

import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResultResponse;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;

/**
 * study.daydayup.wolf.business.union.app.service
 *
 * @author Wingle
 * @since 2020/3/4 12:41 下午
 **/
@Service
public class UnionLoanService implements study.daydayup.wolf.framework.layer.domain.Service {
    @Reference
    private LoanService loanService;
    @Reference
    private ContractService contractService;

    public PayResponse pay(@NonNull PayRequest request) {
        if (null == request.getTradeId()) {
            return null;
        }

        ContractOption option = initContractOption();
        Contract contract = contractService.find(request.getTradeId(), option).notNullData();
        if (validRepayment(contract)) {
            return null;
        }

        createRepayOrder(contract);
        PaymentCreateResponse response = callPayApi(contract);
        return formatPaymentCreateResponse(response);
    }

    private PayResponse formatPaymentCreateResponse(PaymentCreateResponse response) {
        if (response == null) {
            return null;
        }

        return null;
    }

    private void createRepayOrder(Contract contract) {

    }

    private PaymentCreateResponse callPayApi(Contract contract) {
        return null;
    }

    private boolean validRepayment(Contract contract) {
        if (null == contract.getRepaymentTerm()) {
            return false;
        }

        return true;
    }

    public PayResultResponse payResult(TradeId tradeId) {
        return null;
    }

    private ContractOption initContractOption() {
        return ContractOption.builder()
                .withLoanTerm(true)
                .withInstallmentTerm(true)
                .calculateRepayment(true)
                .build();
    }
}
