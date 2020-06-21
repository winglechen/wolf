package study.daydayup.wolf.business.pay.biz.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.PaymentDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.payment.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class PaymentConverter implements Converter {
    public static Payment toModel(PaymentDO paymentDO) {
        if (paymentDO == null) {
            return null;
        }

        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDO, payment);

        return payment;
    }

    public static List<Payment> toModel(List<PaymentDO> paymentDOList) {
        return CollectionUtil.to(paymentDOList, PaymentConverter::toModel);
    }

    public static PaymentDO toDO(Payment payment) {
        if (payment == null) {
            return null;
        }

        PaymentDO paymentDO = new PaymentDO();
        BeanUtils.copyProperties(payment, paymentDO);
        return paymentDO;
    }
}
