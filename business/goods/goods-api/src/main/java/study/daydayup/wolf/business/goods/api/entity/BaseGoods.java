package study.daydayup.wolf.business.goods.api.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Entity;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
public class BaseGoods extends Entity {
    private long goodsId;
    private long orgId;
    private long categoryId;
}
