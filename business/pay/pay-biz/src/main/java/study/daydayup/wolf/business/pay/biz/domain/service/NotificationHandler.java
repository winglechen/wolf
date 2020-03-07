package study.daydayup.wolf.business.pay.biz.domain.service;

import study.daydayup.wolf.business.pay.api.domain.entity.PayNotification;

/**
 * study.daydayup.wolf.business.pay.api.service
 *
 * @author Wingle
 * @since 2020/3/7 7:25 下午
 **/
public interface NotificationHandler {
    boolean isSuccess();
    PayNotification getNotification();
    int handle(PayNotification notification);
}
