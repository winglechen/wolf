package study.daydayup.wolf.business.goods.api.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Entity;
import study.daydayup.wolf.model.annotation.key.BusinessKey;
import study.daydayup.wolf.model.annotation.key.ForeignKey;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
public class BaseGoods extends Entity {
    @BusinessKey
    private long goodsId;
    @ForeignKey
    private long orgId;
    @ForeignKey
    private long categoryId;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum
     */
    private int goodsType;

    private String name;
}
