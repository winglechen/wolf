package study.daydayup.wolf.business.goods.biz.loan;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;

/**
 * study.daydayup.wolf.business.goods.biz.entity
 *
 * @author Wingle
 * @since 2019/12/11 8:43 下午
 **/
@Component
public class LoanEntity extends LoanGoods {
    @Builder
    public LoanEntity(){}
}
