package study.daydayup.wolf.business.union.app.service;

import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.api.service.india.RazorpayService;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResultResponse;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.union.app.service
 *
 * @author Wingle
 * @since 2020/3/4 12:41 下午
 **/
@Component
public class UnionLoanService implements Service {
    @Reference
    private LoanService loanService;
    @Reference
    private RazorpayService razorpayService;

    public PayResponse pay(@NonNull PayRequest request) {
        if (null == request.getTradeId()) {
            return null;
        }

        Order order = loanService.repay(request).notNullData();

        PaymentCreateResponse response = callPayApi(order, PaymentMethodEnum.RAZORPAY.getCode());
        return formatPaymentCreateResponse(response, order);
    }

    public PayResponse audit(Order order, Integer paymentMethod) {
        PaymentCreateResponse response = callPayApi(order, PaymentMethodEnum.RAZORPAY.getCode());
        return formatPaymentCreateResponse(response, order);
    }

    private PayResponse formatPaymentCreateResponse(PaymentCreateResponse paymentCreateResponse, Order order) {
        if (paymentCreateResponse == null || null == order) {
            return null;
        }

        return PayResponse.builder()
                .tradeNo(order.getTradeNo())

                .paymentMethod(paymentCreateResponse.getPaymentMethod())
                .paymentNo(paymentCreateResponse.getPaymentNo())
                .amount(paymentCreateResponse.getAmount())

                .payArgs(paymentCreateResponse.getAttachment())
                .build();
    }

    private PaymentCreateResponse callPayApi(Order order, Integer paymentMethod) {
        PaymentCreateRequest request = PaymentCreateRequest.builder()
                .paymentMethod(paymentMethod)
                .tradeNo(order.getTradeNo())
                .duplicateCheck(false)

                .payeeId(order.getSellerId())
                .payeeName(order.getSellerName())
                .payerId(order.getBuyerId())
                .payerName(order.getBuyerName())

                .amount(order.getAmount())
                .currency(order.getCurrency())

                .build();

        return razorpayService.create(request).notNullData();
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
