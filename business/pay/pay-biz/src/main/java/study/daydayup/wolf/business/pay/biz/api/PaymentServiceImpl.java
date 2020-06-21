package study.daydayup.wolf.business.pay.biz.api;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentQuery;
import study.daydayup.wolf.business.pay.api.service.PaymentService;
import study.daydayup.wolf.business.pay.biz.dal.dao.PaymentDAO;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/6/21 5:43 下午
 **/
@RpcService
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDAO paymentDAO;
    @Override
    public Result<Page<Payment>> query(@Validated PaymentQuery query, PageRequest pageRequest) {
        if (StringUtil.notBlank(query.getPaymentNo())) {
            return byPaymentNo(query.getPaymentNo(), query.getPayeeId(), pageRequest);
        }

        if (StringUtil.notBlank(query.getTradeNo())) {
            return byPaymentNo(query.getTradeNo(), query.getPayeeId(), pageRequest);
        }

        if (StringUtil.notBlank(query.getOutTradeNo())) {
            return byPaymentNo(query.getOutTradeNo(), query.getPayeeId(), pageRequest);
        }

        if (null != query.getCreatedStart() || null != query.getCreatedEnd()) {
            return byRange(query, pageRequest);
        }

        return byState(query, pageRequest);
    }

    private Result<Page<Payment>> byPaymentNo(@NonNull String paymentNo, @NonNull Long payeeId, PageRequest pageRequest) {


        return null;
    }

    private Result<Page<Payment>> byTradeNo(@NonNull String tradeNo, @NonNull Long payeeId, PageRequest pageRequest) {
        return null;
    }

    private Result<Page<Payment>> byOutTradeNo(@NonNull String outTradeNo, @NonNull Long payeeId, PageRequest pageRequest) {
        return null;
    }

    private Result<Page<Payment>> byState(@Validated PaymentQuery query, PageRequest pageRequest) {
        return null;
    }

    private Result<Page<Payment>> byRange(@Validated PaymentQuery query, PageRequest pageRequest) {
        return null;
    }




}
