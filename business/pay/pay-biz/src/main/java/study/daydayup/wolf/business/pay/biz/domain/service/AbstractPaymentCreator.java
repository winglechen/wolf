package study.daydayup.wolf.business.pay.biz.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.domain.exception.TradeNoCreateFailException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentStateEnum;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.string.id.TradeNo;
import study.daydayup.wolf.common.util.lang.DecimalUtil;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.pay.biz.service
 *
 * @author Wingle
 * @since 2020/2/29 3:19 下午
 **/
@Component
public abstract class AbstractPaymentCreator extends AbstractPaymentDomainService implements PaymentCreator {
    protected static final int INSERT_RETRY_TIMES = 3;
    protected PaymentCreateRequest createRequest;
    protected ObjectMap attachment;
    protected String apiResponse;

    @Override
    public void initPayment(boolean duplicateCheck) {
        if (!duplicateCheck) {
            createPayment(createRequest);
            return;
        }

        if (!checkExistence(createRequest.getTradeNo())) {
            createPayment(createRequest);
        }
    }

    @Override
    public PaymentCreateResponse create(@Validated PaymentCreateRequest request) {
        this.createRequest = request;

        validateRequest();
        initPayment(request.isDuplicateCheck());

        callPayEpi();
        logCreateResponse(apiResponse);
        parseCreateResponse();

        savePayment();
        return formatResponse();
    }

    @Override
    public void validateRequest() {
    }

    @Override
    public PaymentCreateResponse formatResponse() {
        PaymentCreateResponse response = new PaymentCreateResponse();
        response.setPaymentNo(payment.getPaymentNo());
        response.setAmount(payment.getAmount());
        response.setPaymentChannel(payment.getPaymentMethod());
        response.setPayArgs(attachment.getMap());

        return response;
    }

    protected boolean checkExistence(String tradeNo) {
        Payment p = findByTradeNo(tradeNo);
        if (p == null) {
            return false;
        }

        payment = p;
        return true;
    }

    protected void createPayment(PaymentCreateRequest request) {
        payment = new Payment();
        BeanUtils.copyProperties(request, payment);

        String paymentNo = TradeNo.builder()
                .tradePhase(TradePhaseEnum.PAYMENT_PHASE)
                .accountId(request.getPayerId())
                .build()
                .create();

        payment.setId(null);
        payment.setPaymentNo(paymentNo);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setState(PaymentStateEnum.WAIT_TO_PAY.getCode());
        attachment = new ObjectMap();

        addPayment();
    }

    protected void addPayment() {
        Long id = null;
        for (int i = 0; i < INSERT_RETRY_TIMES; i++) {
            id = doAdding();
            if (id == null) {
                changePaymentNo();
                continue;
            }
        }

        if (id == null) {
            throw new TradeNoCreateFailException();
        }
        payment.setId(id);
    }

    protected void changePaymentNo() {
        String tradeNo = payment.getTradeNo();
        if (tradeNo == null) {
            throw new TradeNoCreateFailException("payment.tradeNo is null");
        }

        String newTradeNo = TradeNo.recreate(tradeNo);
        if (newTradeNo == null) {
            throw new TradeNoCreateFailException("recreate tradeNo fail");
        }

        payment.setPaymentNo(newTradeNo);
    }

    protected Long doAdding() {
        try {
            return paymentRepository.add(payment);
        } catch (DuplicateKeyException e) {
            return null;
        }
    }

    protected BigDecimal getAmount() {
        BigDecimal amount = createRequest.getAmount();
        amount = DecimalUtil.scale(amount, 2);

        return amount;
    }

}
