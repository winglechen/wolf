package study.daydayup.wolf.business.uc.crm.biz.customer.credit.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity.CreditLineEntity;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.repository
 *
 * @author Wingle
 * @since 2020/3/10 4:59 下午
 **/
@Component
public class CreditLineRepository extends AbstractRepository implements Repository {
    public int save(CreditLineEntity entity) {
        return 0;
    }

    public CreditLineEntity find(@NonNull Long accountId, @NonNull Long orgId) {
        return null;
    }
}
