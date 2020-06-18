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
    void initPayment(boolean duplicateCheck);

    void validateRequest();
    void callPayEpi();
    void parseCreateResponse();
    PaymentCreateResponse formatResponse();
}
