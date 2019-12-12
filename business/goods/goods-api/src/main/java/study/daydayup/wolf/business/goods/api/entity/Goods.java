package study.daydayup.wolf.business.goods.api.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.business.goods.api.entity.goods.BaseGoods;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
public class Goods extends BaseGoods {
    @Builder
    public Goods() {}
    private String pics;
    private String skuInfo;
    private String detail;

    private List<Sku> skuList;

}
