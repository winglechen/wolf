package study.daydayup.wolf.business.goods.api.entity;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.model.type.string.URI;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
@Builder
public class Goods extends BaseGoods {
    private List<String> pics;
    private String skuInfo;
    private String detail;
    private List<Sku> skuList;
}
