package study.daydayup.wolf.business.pay.biz.api;

import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayConfigException;
import study.daydayup.wolf.business.pay.api.service.OnionPayConfigService;
import study.daydayup.wolf.common.util.lang.EnumUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/7/6 9:16 下午
 **/
@RpcService
public class OnionPayConfigServiceImpl implements OnionPayConfigService {
    @Resource
    private PayConfig payConfig;

    @Override
    public Result<String> findReturnUrl() {
        PaySupplier supplier = getSupplierConfig(PaymentChannelEnum.ONIONPAY);
        String returnUrl = supplier.getReturnUrl();
        return Result.ok(returnUrl);
    }

    @Override
    public Result<String> findReturnUrl(int paymentChannel) {
        PaySupplier supplier = getSupplierConfig(paymentChannel);
        String returnUrl = supplier.getReturnUrl();
        return Result.ok(returnUrl);
    }

    protected PaySupplier getSupplierConfig(int paymentChannel) {
        PaymentChannelEnum channelEnum = EnumUtil.codeOf(paymentChannel, PaymentChannelEnum.class);

        return getSupplierConfig(channelEnum);
    }

    protected PaySupplier getSupplierConfig(PaymentChannelEnum paymentChannel) {
        String configKey = paymentChannel.getName();

        String errorMsg;
        if (null == payConfig.getSupplier()) {
            throw new InvalidPayConfigException("payConfig.supplier not found");
        }

        PaySupplier supplier = payConfig.getSupplier().get(configKey);
        if (supplier == null) {
            errorMsg = StringUtil.join("payConfig.supplier: ", configKey, " not found" );
            throw new InvalidPayConfigException(errorMsg );
        }

        return supplier;
    }
}
