package study.daydayup.wolf.business.pay.biz.service.india.onionpay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentCreator;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.onionpay
 *
 * @author Wingle
 * @since 2020/7/2 4:10 下午
 **/
@Slf4j
@Component
public class OnionPayCreator extends AbstractPaymentCreator implements PaymentCreator {

    @Override
    public void initPayment(boolean duplicateCheck) {
    }

    @Override
    public void callPayEpi() {
    }

    @Override
    public void logCreateResponse(String data) {
    }

    @Override
    public void parseCreateResponse() {

    }

    @Override
    public void savePayment() {
    }

    @Override
    public PaymentCreateResponse formatResponse() {
        setResponseAttachment();

        PaymentCreateResponse response = new PaymentCreateResponse();
        response.setPaymentNo(payment.getPaymentNo());
        response.setAmount(payment.getAmount());
        response.setPaymentChannel(PaymentChannelEnum.DLOCAL.getCode());
        response.setPayArgs(attachment.getMap());

        return response;
    }

    private void setResponseAttachment() {
        attachment.put("payUrl", "url");
        attachment.put("orderId", payment.getPaymentNo());
        attachment.put("orderAmount", getAmount());
        attachment.put("orderCurrency", "INR");
    }



}
