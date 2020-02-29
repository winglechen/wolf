package study.daydayup.wolf.business.pay.biz.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.entity.Payment;
import study.daydayup.wolf.business.pay.api.entity.PaymentLog;
import study.daydayup.wolf.business.pay.api.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.api.enums.PaymentStateEnum;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentRepository;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.id.TradeNo;

import javax.annotation.Resource;

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
    protected ObjectMap attachment;
    protected String apiResponse;

    @Resource
    private PaymentRepository paymentRepository;
    @Resource
    private PaymentLogRepository logRepository;

    @Override
    public PaymentCreateResponse create(@Validated PaymentCreateRequest request) {
        this.request = request;

        validateRequest();
        createPayment();
        callPayApi();
        logApiResponse();
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
        payment.setState(PaymentStateEnum.WAIT_TO_PAY.getCode());
        attachment = new ObjectMap();
    }


    @Override
    public void logApiResponse() {
        PaymentLog log = PaymentLog.builder()
                .paymentNo(payment.getPaymentNo())
                .payeeId(payment.getPayeeId())
                .payerId(payment.getPayerId())
                .tradeNo(payment.getTradeNo())
                .paymentMethod(payment.getPaymentMethod())
                .logType(PaymentLogTypeEnum.PAY_REQUEST.getCode())
                .data(apiResponse)
                .build();

        logRepository.add(log);
    }

    @Override
    public void savePayment() {
        paymentRepository.add(payment);
    }

    @Override
    public PaymentCreateResponse formatResponse() {
        PaymentCreateResponse response = new PaymentCreateResponse();
        response.setPaymentNo(payment.getPaymentNo());
        response.setAmount(payment.getAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setAttachment(attachment);

        return response;
    }
}
