package study.daydayup.wolf.business.pay.biz.domain.repository;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.entity.Payment;
import study.daydayup.wolf.business.pay.biz.dal.dao.PaymentDAO;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.PaymentDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.context.RpcContext;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.pay.biz.domain.repository
 *
 * @author Wingle
 * @since 2020/2/29 6:37 下午
 **/
@Component
public class PaymentRepository extends AbstractRepository implements Repository {
    @Resource
    private RpcContext context;
    @Resource
    private PaymentDAO dao;

    public Payment find(@NonNull String paymentNo) {
        PaymentDO paymentDO = dao.selectByPaymentNo(paymentNo);
        return toModel(paymentDO);
    }

    public List<Payment> findByTradeNo(@NonNull String tradeNo, Integer state) {
        List<PaymentDO> paymentDOS = dao.selectByTradeNo(tradeNo, state);
        return CollectionUtil.to(paymentDOS, this::toModel);
    }

    public int add(@NonNull Payment payment) {
        PaymentDO paymentDO = toDo(payment);
        paymentDO.setCreatedAt(context.getRequestTime());

        return dao.insertSelective(paymentDO);
    }

    public int save(@NonNull Payment payment) {
        if (null == payment.getPaymentNo()) {
            return 0;
        }

        String paymentNo = payment.getPaymentNo();

        PaymentDO paymentDO = toDo(payment);
        clearReadOnlyProperties(paymentDO);
        paymentDO.setUpdatedAt(context.getRequestTime());

        return dao.updateByPaymentNo(paymentDO, paymentNo);
    }

    public void clearReadOnlyProperties(PaymentDO paymentDO) {
        paymentDO.setAmount(null);
        paymentDO.setPayeeId(null);
        paymentDO.setPayeeName(null);
        paymentDO.setPayerId(null);
        paymentDO.setPayerName(null);
        paymentDO.setCurrency(null);
        paymentDO.setPaymentType(null);
        paymentDO.setPaymentType(null);
        paymentDO.setTradeNo(null);
    }

    public PaymentDO toDo(@NonNull Payment payment) {
        PaymentDO paymentDO = new PaymentDO();
        BeanUtils.copyProperties(payment, paymentDO);

        return paymentDO;
    }

    public Payment toModel(PaymentDO paymentDO) {
        if (paymentDO == null) {
            return null;
        }

        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDO, payment);

        return payment;
    }

}
