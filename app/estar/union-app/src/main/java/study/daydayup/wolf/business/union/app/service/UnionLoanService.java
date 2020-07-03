package study.daydayup.wolf.business.union.app.service;

import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.service.PayService;
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

    @Reference(timeout = 5000)
    private PayService payService;

    public PayResponse pay(@NonNull PayRequest payRequest, Integer paymentMethod) {
        if (null == payRequest.getTradeId()) {
            return null;
        }

        Order order = loanService.repay(payRequest).notNullData();

        if (null == paymentMethod) {
            paymentMethod = PaymentChannelEnum.RAZORPAY.getCode();
        }
        PaymentCreateRequest request = formatPaymentCreateRequest(order, paymentMethod);
        PaymentCreateResponse response = callPayApi(request);
        return formatPaymentCreateResponse(response, order);
    }

    public PayResponse audit(@Validated Order order, Integer paymentMethod) {
        if (null == paymentMethod) {
            paymentMethod = PaymentChannelEnum.DOKYPAY.getCode();
        }

        PaymentCreateRequest request = formatPaymentCreateRequest(order, paymentMethod);
        PaymentCreateResponse response = callPayApi(request);
        return formatPaymentCreateResponse(response, order);
    }

    private PayResponse formatPaymentCreateResponse(PaymentCreateResponse paymentCreateResponse, Order order) {
        if (paymentCreateResponse == null || null == order) {
            return null;
        }

        return PayResponse.builder()
                .tradeNo(order.getTradeNo())

                .paymentMethod(paymentCreateResponse.getPaymentChannel())
                .paymentNo(paymentCreateResponse.getPaymentNo())
                .amount(paymentCreateResponse.getAmount())

                .payArgs(paymentCreateResponse.getPayArgs())
                .build();
    }

    private PaymentCreateRequest formatPaymentCreateRequest(Order order, Integer paymentMethod) {
        return PaymentCreateRequest.builder()
                .paymentChannel(paymentMethod)
                .tradeNo(order.getTradeNo())
                .duplicateCheck(false)

                .payeeId(order.getSellerId())
                .payeeName(order.getSellerName())
                .payerId(order.getBuyerId())
                .payerName(order.getBuyerName())

                .amount(order.getAmount())
                .currency(order.getCurrency())

                .build();
    }

    private PaymentCreateResponse callPayApi(PaymentCreateRequest request) {
        return payService.create(request).notNullData();
    }

    private PaymentCreateResponse callRazorPayApi(PaymentCreateRequest request) {
        return payService.create(request).notNullData();
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
