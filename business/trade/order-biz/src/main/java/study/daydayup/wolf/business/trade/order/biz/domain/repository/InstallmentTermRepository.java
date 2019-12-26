package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.vo.contract.InstallmentTerm;
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
public class InstallmentTermRepository extends AbstractRepository implements Repository {
    public void add(List<InstallmentTerm> installmentTerms) {

    }

    public void save(List<InstallmentTerm> locker, List<InstallmentTerm> changes) {

    }

    public List<InstallmentTerm> find(TradeId tradeId) {
        return null;
    }

}