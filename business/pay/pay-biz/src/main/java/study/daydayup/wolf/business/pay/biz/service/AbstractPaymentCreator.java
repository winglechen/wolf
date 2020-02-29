package study.daydayup.wolf.business.pay.biz.service;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.entity.Payment;
import study.daydayup.wolf.business.pay.api.entity.PaymentLog;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.id.TradeNo;
import study.daydayup.wolf.framework.layer.context.ObjectContext;

/**
 * study.daydayup.wolf.business.pay.biz.service
 *
 * @author Wingle
 * @since 2020/2/29 3:19 下午
 **/
@Component
public abstract class AbstractPaymentCreator implements PaymentCreator {
    protected PaymentCreateRequest request;
    protected Payment payment;
    protected PaymentLog paymentLog;
    protected ObjectContext context;

    @Override
    public PaymentCreateResponse create(@Validated PaymentCreateRequest request) {
        this.request = request;
        this.context = new ObjectContext();

        validateRequest();
        createPayment();
        callPayApi();
        logPayRequest();
        savePayment();

        return formatResponse();
    }

    @Override
    public void validateRequest() {
    }

    @Override
    public void createPayment() {
        payment = new Payment();
        BeanUtils.copyProperties(request, payment);

        String paymentNo = TradeNo.builder()
                .tradePhase(TradePhaseEnum.PAYMENT_PHASE)
                .accountId(request.getPayerId())
                .build()
                .create();
        payment.setPaymentNo(paymentNo);
    }


    @Override
    public void logPayRequest() {

    }

    @Override
    public void savePayment() {

    }

    @Override
    public PaymentCreateResponse formatResponse() {
        return null;
    }
}
