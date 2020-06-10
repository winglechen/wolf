package study.daydayup.wolf.business.pay.biz.domain.service;

import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;

/**
 * study.daydayup.wolf.business.pay.biz.service
 *
 * @author Wingle
 * @since 2020/2/28 12:29 下午
 **/
public interface PaymentCreator extends PaymentDomainService {
    PaymentCreateResponse create(PaymentCreateRequest request);


    void validateRequest();
    void initPayment();

    void callPayEpi();
    void logEpiResponse();

    void parseEpiResponse();
    void savePayment();
    PaymentCreateResponse formatResponse();
}
