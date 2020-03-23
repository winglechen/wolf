package study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentStateEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.InvalidPayoutAccountException;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentRepository;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccount;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.id.TradeNo;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.domain.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout
 *
 * @author Wingle
 * @since 2020/3/23 4:56 下午
 **/
@Component
public class RazorPayoutService implements Service {
    private RazorAccount account;
    private PayoutRequest payoutRequest;
    protected Payment payment;
    protected ObjectMap attachment;

    @Resource
    private PaymentRepository paymentRepository;
    @Resource
    private PaymentLogRepository logRepository;
    @Resource
    private RazorConfig config;

    public PayoutResponse create(RazorAccount account, @Validated PayoutRequest request) {
        validAccount(account);

        this.account = account;
        this.payoutRequest = request;
        initPayment();

        return null;
    }

    private void validAccount(RazorAccount account) {
        if (null == account || null == account.getPayerId() || null == account.getPayeeId()) {
            throw new InvalidPayoutAccountException("Razorpay payout Can't find customer bankCard info");
        }

        if (null == account.getAccountId()) {
            throw new InvalidPayoutAccountException("Razorpay payout Can't find fund account info");
        }
    }

    public void initPayment() {
        if (!payoutRequest.isDuplicateCheck()) {
            createPayment();
            return;
        }

        if (!checkExistence()) {
            createPayment();
        }
    }

    private boolean checkExistence() {
        String tradeNo = payoutRequest.getTradeNo();
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
        BeanUtils.copyProperties(payoutRequest, payment);

        String paymentNo = TradeNo.builder()
                .tradePhase(TradePhaseEnum.PAYMENT_PHASE)
                .accountId(payoutRequest.getPayerId())
                .build()
                .create();

        payment.setPaymentNo(paymentNo);
        payment.setPaymentMethod(payoutRequest.getPaymentMethod());
        payment.setState(PaymentStateEnum.WAIT_TO_PAY.getCode());
        attachment = new ObjectMap();
    }
}
