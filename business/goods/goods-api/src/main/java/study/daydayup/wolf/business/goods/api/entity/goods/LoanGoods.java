package study.daydayup.wolf.business.goods.api.entity.goods;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.business.goods.api.entity.Goods;

/**
 * study.daydayup.wolf.business.goods.api.entity.goods
 *
 * @author Wingle
 * @since 2019/12/11 5:17 下午
 **/
@Data
@Builder
public class LoanGoods extends Goods {
    private int duration;
}
