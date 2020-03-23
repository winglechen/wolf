package study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout;

import com.razorpay.RazorpayException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.entity.PaymentLog;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentStateEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.InvalidPayoutAccountException;
import study.daydayup.wolf.business.pay.api.domain.exception.PayoutFailException;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentRepository;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.ClientFactory;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.PayoutClient;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor.Payout;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.enums.PayoutModeEnum;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.enums.PayoutPurposeEnum;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccount;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.id.TradeNo;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.framework.layer.domain.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout
 *
 * @author Wingle
 * @since 2020/3/23 4:56 下午
 **/
@Slf4j
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
        callEpi();
        savePayment();

        return formatResponse();
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

    public void callEpi() {
        JSONObject request = createPayoutRequest();
        log.debug("razorpay contact create request: {}", request);

        try {
            PayoutClient client = ClientFactory.createPayoutClient(config.getKeyId(), config.getKeySecret());
            Payout payout = client.create(request);
            parseResponse(payout);
        } catch (RazorpayException e) {
            throw new PayoutFailException("create razorpay contact fail");
        }
    }

    private void parseResponse(Payout payout) {
        log.debug("razorpay contact payout response: {}", payout);
        if (payout == null || null == payout.get("id")) {
            throw new PayoutFailException("create razorpay payout fail");
        }

        logEpiResponse(payout.toString());
        payment.setOutTradeNo(payout.get("id"));
    }

    public void savePayment() {
        if (null == payment || null == payment.getOutTradeNo()) {
            return;
        }
        paymentRepository.add(payment);
    }

    private PayoutResponse formatResponse() {
        return null;
    }

    private JSONObject createPayoutRequest() {
        JSONObject request = new JSONObject();

        request.put("account_number", account.getAccountNumber());
        request.put("fund_account_id", account.getContactId());
        request.put("amount", getAmount(payoutRequest.getAmount()));
        request.put("currency", "INR");
        request.put("mode", PayoutModeEnum.IMPS.getName());
        request.put("purpose", PayoutPurposeEnum.PAYOUT.getName());
        request.put("queue_if_low_balance", true);
        request.put("reference_id", payment.getPaymentNo());

        return request;
    }

    private int getAmount(BigDecimal amount) {
        amount = amount.multiply(BigDecimal.valueOf(100));
        return DecimalUtil.toInt(amount);
    }

    public void logEpiResponse(String response) {
        PaymentLog log = PaymentLog.builder()
                .paymentNo(payment.getPaymentNo())
                .payeeId(payment.getPayeeId())
                .payerId(payment.getPayerId())
                .tradeNo(payment.getTradeNo())
                .paymentMethod(payment.getPaymentMethod())
                .logType(PaymentLogTypeEnum.PAYOUT_REQUEST.getCode())
                .data(response)
                .build();

        logRepository.add(log);
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
