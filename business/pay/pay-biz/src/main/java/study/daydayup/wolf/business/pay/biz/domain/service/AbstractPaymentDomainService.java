package study.daydayup.wolf.business.pay.biz.domain.service;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.entity.PaymentLog;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentStateEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayConfigException;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayRequestException;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.PaymentNotFoundException;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentRepository;
import study.daydayup.wolf.business.uc.setting.agent.CompanySettingAgent;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.pay.biz.domain.service
 *
 * @author Wingle
 * @since 2020/6/10 8:35 下午
 **/
@Component
public abstract class AbstractPaymentDomainService implements PaymentDomainService {
    protected PaySupplier supplierConfig;
    protected Payment payment;

    @Resource
    protected PayConfig payConfig;
    @Resource
    protected PaymentRepository paymentRepository;
    @Resource
    private PaymentLogRepository logRepository;
    @Resource
    protected CompanySettingAgent companySettingAgent;


    @Override
    public void initConfig(@NonNull String configKey) {
        initConfig(configKey, null);
    }

    @Override
    public void initConfig(@NonNull String configKey, Long payeeId) {
        String errorMsg;
        if (null == payConfig.getSupplier()) {
            throw new InvalidPayConfigException("payConfig.supplier not found");
        }

        PaySupplier tmp = payConfig.getSupplier().get(configKey);
        if (tmp == null) {
            errorMsg = StringUtil.join("payConfig.supplier: ", configKey, " not found" );
            throw new InvalidPayConfigException(errorMsg );
        }

        supplierConfig = tmp;
        mergeOrgSetting(configKey, payeeId);
    }

    protected void mergeOrgSetting(@NonNull String configKey, Long orgId) {
        if (orgId == null) {
            return;
        }
        String errorMsg;
        companySettingAgent.init(orgId, false);
        companySettingAgent.namespace(PayConfig.PAY_NAMESPACE);
        ObjectMap orgSetting = companySettingAgent.getAll();
        if (orgSetting == null || null == orgSetting.get("supplier")) {
            errorMsg = StringUtil.join("payConfig for org: ", orgId, " not found");
            throw new InvalidPayConfigException(errorMsg);
        }

        PaySupplier supplier = orgSetting.getJSONObject("supplier").getObject(configKey, PaySupplier.class);
        if (supplier == null) {
            errorMsg = StringUtil.join("payConfig(", configKey, ") for org: ", orgId , " not found");
            throw new InvalidPayConfigException(errorMsg);
        }

        supplierConfig = supplier;
    }

    @Override
    public void loadPayment(String paymentNo) {
        if (StringUtil.isBlank(paymentNo)) {
            throw new InvalidPayRequestException("paymentNo can't be null");
        }

        Payment tmp = findByPaymentNo(paymentNo);
        if (tmp == null) {
            throw new PaymentNotFoundException(paymentNo);
        }

        payment = tmp;
    }

    @Override
    public void savePayment() {
        paymentRepository.save(payment);
    }

    @Override
    public Payment findByPaymentNo(@NonNull String paymentNo) {
        return paymentRepository.find(paymentNo);
    }

    @Override
    public Payment findByTradeNo(String tradeNo) {
        Integer state = PaymentStateEnum.WAIT_TO_PAY.getCode();

        List<Payment> payments = paymentRepository.findByTradeNo(tradeNo, state);
        if (CollectionUtil.isEmpty(payments) || null == payments.get(0)) {
            return null;
        }

        return payments.get(0);
    }

    @Override
    public void logCreateResponse(String data) {
        PaymentLog log = PaymentLog.builder()
                .paymentNo(payment.getPaymentNo())
                .payeeId(payment.getPayeeId())
                .payerId(payment.getPayerId())
                .tradeNo(payment.getTradeNo())
                .paymentMethod(payment.getPaymentMethod())
                .logType(PaymentLogTypeEnum.PAY_REQUEST.getCode())
                .data(data)
                .build();

        logRepository.add(log);
    }

    @Override
    public void logSubscribeResponse(int logType, int paymentMethod, String data) {
        if (StringUtil.isBlank(data)) {
            return;
        }

        PaymentLog log = PaymentLog.builder()
                .logType(logType)
                .paymentMethod(paymentMethod)
                .data(data)
                .build();
        logRepository.add(log);
    }
}
