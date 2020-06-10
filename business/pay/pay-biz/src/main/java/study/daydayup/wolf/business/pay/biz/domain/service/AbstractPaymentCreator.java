package study.daydayup.wolf.business.pay.biz.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.entity.PaymentLog;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentStateEnum;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentRepository;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.string.id.TradeNo;
import study.daydayup.wolf.common.util.collection.CollectionUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.pay.biz.service
 *
 * @author Wingle
 * @since 2020/2/29 3:19 下午
 **/
@Component
public abstract class AbstractPaymentCreator extends AbstractPaymentDomainService implements PaymentCreator {
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
        initPayment();

        callPayEpi();
        logEpiResponse();
        parseEpiResponse();

        savePayment();
        return formatResponse();
    }

    @Override
    public void validateRequest() {
    }

    @Override
    public void initPayment() {
        if (!request.isDuplicateCheck()) {
            createPayment();
            return;
        }

        if (!checkExistence()) {
            createPayment();
        }
    }

    @Override
    public void logEpiResponse() {
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

    private boolean checkExistence() {
        String tradeNo = request.getTradeNo();
        Integer state = PaymentStateEnum.WAIT_TO_PAY.getCode();

        List<Payment> payments = paymentRepository.findByTradeNo(tradeNo, state);
        if (CollectionUtil.isEmpty(payments) || null == payments.get(0)) {
            return false;
        }

        payment = payments.get(0);
        return true;
    }

    private void createPayment() {
        payment = new Payment();
        BeanUtils.copyProperties(request, payment);

        String paymentNo = TradeNo.builder()
                .tradePhase(TradePhaseEnum.PAYMENT_PHASE)
                .accountId(request.getPayerId())
                .build()
                .create();

        payment.setPaymentNo(paymentNo);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setState(PaymentStateEnum.WAIT_TO_PAY.getCode());
        attachment = new ObjectMap();
    }

}
