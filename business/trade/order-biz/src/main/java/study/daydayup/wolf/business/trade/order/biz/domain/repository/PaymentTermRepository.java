package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.contract.PaymentTerm;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class PaymentTermRepository extends AbstractRepository implements Repository {
    public void add(PaymentTerm paymentTerm) {

    }

    public void save(PaymentTerm key, PaymentTerm changes) {

    }

    public PaymentTerm find(TradeId tradeId) {
        return null;
    }

}
