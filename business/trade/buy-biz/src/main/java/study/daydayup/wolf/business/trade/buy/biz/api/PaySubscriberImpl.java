package study.daydayup.wolf.business.trade.buy.biz.api;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.enums.PaymentReturnEnum;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.buy.base.TradeNotification;
import study.daydayup.wolf.business.trade.api.dto.buy.base.TradeNotificationResponse;
import study.daydayup.wolf.business.trade.api.service.buy.PaySubscriber;
import study.daydayup.wolf.business.trade.buy.biz.base.entity.OrderEntity;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanOrderEntity;
import study.daydayup.wolf.business.trade.buy.biz.loan.repository.LoanOrderRepository;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.buy.biz.api
 *
 * @author Wingle
 * @since 2020/3/9 11:26 上午
 **/

@RpcService(protocol = "dubbo")
public class PaySubscriberImpl implements PaySubscriber {
    private Order order;

    @Resource
    private LoanOrderRepository orderRepository;

    @Override
    public Result<TradeNotificationResponse> subscribe(@Validated TradeNotification notification) {
        int status = updateOrder(notification);
        if (PaymentReturnEnum.SUCCESS.getCode() != status) {
            return fail(status);
        }

        status = updateRelatedTrade();
        if (PaymentReturnEnum.SUCCESS.getCode() != status) {
            return fail(status);
        }

        return success();
    }

    private Result<TradeNotificationResponse> success() {
        TradeNotificationResponse response = TradeNotificationResponse.builder()
                .success(true)
                .cause(PaymentReturnEnum.SUCCESS.getCode())
                .build();

        return Result.ok(response);
    }

    private Result<TradeNotificationResponse> fail(int status) {
        TradeNotificationResponse response = TradeNotificationResponse.builder()
                .success(false)
                .cause(status)
                .build();

        return Result.fail(10000, "fail", response);
    }

    private int updateOrder(TradeNotification notification) {
        TradeId orderId = new TradeId();
        orderId.setTradeNo(notification.getTradeNo());
        orderId.setBuyerId(notification.getPayerId());
        orderId.setSellerId(notification.getPayeeId());

        LoanOrderEntity orderEntity = orderRepository.find(orderId);
        int status = orderEntity.paid(notification);
        if (PaymentReturnEnum.SUCCESS.getCode() != status) {
            return status;
        }

        orderRepository.save(orderEntity);
        order = orderEntity.getModel();

        return PaymentReturnEnum.SUCCESS.getCode();
    }

    private int updateRelatedTrade() {
        return 0;
    }
}
