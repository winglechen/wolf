package study.daydayup.wolf.business.trade.order.biz.domain.repository.contract;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.TaxTerm;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class TaxTermRepository extends AbstractRepository implements Repository {
    public void add(TaxTerm taxTerm) {

    }

    public void save(TaxTerm key, TaxTerm changes) {

    }

    public TaxTerm find(TradeId tradeId) {
        return null;
    }

}
