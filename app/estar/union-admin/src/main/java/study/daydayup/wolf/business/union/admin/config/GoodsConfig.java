package study.daydayup.wolf.business.union.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.enums.GoodsStateEnum;
import study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum;
import study.daydayup.wolf.business.goods.api.enums.StockTypeEnum;
import study.daydayup.wolf.common.lang.enums.currency.IndianRupeeEnum;
import study.daydayup.wolf.common.lang.enums.unit.UnitEnum;

/**
 * study.daydayup.wolf.business.union.admin.config
 *
 * @author Wingle
 * @since 2019/12/11 4:16 下午
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wolf.goods")
public class GoodsConfig {
    private int stockType           = StockTypeEnum.NO_STOCK.getCode();
    private int categoryId          = 0;
    private int state               = GoodsStateEnum.SALABLE.getCode();
    private int currency            = IndianRupeeEnum.RUPEE.getCode();
    private int chargeUnit          = UnitEnum.NONE.getCode();

}
