package study.daydayup.wolf.business.trade.order.biz.domain.repository.contract;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.PostageTerm;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class PostageTermRepository extends AbstractRepository implements Repository {
    public void add(PostageTerm postageTerm) {

    }

    public void save(PostageTerm key, PostageTerm changes) {

    }

    public PostageTerm find(TradeId tradeId) {
        return null;
    }

    public List<PostageTerm> find(TradeIds tradeIds) {
        return null;
    }
}
