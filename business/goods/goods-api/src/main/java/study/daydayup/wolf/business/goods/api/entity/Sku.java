package study.daydayup.wolf.business.goods.api.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.Entity;
import study.daydayup.wolf.common.model.annotation.column.ParentKey;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:01 PM
 **/
@Data
public class Sku implements Entity  {
    private Long skuId;
    @ParentKey
    private Long goodsId;

}
