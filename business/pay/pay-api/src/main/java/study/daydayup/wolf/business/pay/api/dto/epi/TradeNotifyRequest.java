package study.daydayup.wolf.business.pay.api.dto.epi;

import lombok.Data;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.pay.api.dto.base
 *
 * @author Wingle
 * @since 2020/3/1 12:33 上午
 **/
@Data
public class TradeNotifyRequest implements Request {
    private Payment payment;

}
