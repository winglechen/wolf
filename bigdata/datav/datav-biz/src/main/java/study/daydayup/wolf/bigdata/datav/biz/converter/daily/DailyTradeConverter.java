package study.daydayup.wolf.bigdata.datav.biz.converter.daily;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyTrade;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyTradeDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.bigdata.datav.biz.converter.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:24 上午
 **/
public class DailyTradeConverter implements Converter {
    public static DailyTradeDO toDO(DailyTrade trade) {
        if (trade == null) {
            return null;
        }

        DailyTradeDO tradeDO = new DailyTradeDO();
        BeanUtils.copyProperties(trade, tradeDO);
        return tradeDO;
    }

    public static DailyTrade toModel(DailyTradeDO tradeDO) {
        if (tradeDO == null) {
            return null;
        }

        DailyTrade trade = new DailyTrade();
        BeanUtils.copyProperties(tradeDO, trade);

        return trade;
    }

    public static List<DailyTrade> toModel(List<DailyTradeDO> tradeDOList) {
        return CollectionUtil.to(tradeDOList, DailyTradeConverter::toModel);
    }

}
