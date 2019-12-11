package study.daydayup.wolf.business.goods.api.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.Entity;
import study.daydayup.wolf.model.annotation.column.ParentKey;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:01 PM
 **/
@Data
public class Sku extends Entity {
    private long skuId;
    @ParentKey
    private long goodsId;

}
