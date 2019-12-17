package study.daydayup.wolf.business.trade.buy.biz.epi;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.service.GoodsService;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeGoods;

import java.util.List;


/**
 * study.daydayup.wolf.business.trade.buy.biz.epi
 *
 * @author Wingle
 * @since 2019/12/17 8:29 下午
 **/
@Component
public class GoodsEpi {
    @Reference
    private GoodsService goodsService;

    List<TradeGoods> fetch(GoodsRequest request) {
        return null;
    }
}
