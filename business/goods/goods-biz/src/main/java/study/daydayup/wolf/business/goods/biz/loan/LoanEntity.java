package study.daydayup.wolf.business.goods.biz.loan;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;

/**
 * study.daydayup.wolf.business.goods.biz.entity
 *
 * @author Wingle
 * @since 2019/12/11 8:43 下午
 **/
@Component
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class LoanEntity extends LoanGoods {
    public LoanEntity(){}
}
