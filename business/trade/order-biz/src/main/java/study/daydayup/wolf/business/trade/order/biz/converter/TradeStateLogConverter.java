package study.daydayup.wolf.business.trade.order.biz.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.trade.api.domain.entity.trade.TradeStateLog;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.TradeStateLogDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.converter
 *
 * @author Wingle
 * @since 2020/1/17 10:07 下午
 **/
public class TradeStateLogConverter implements Converter {
    public static TradeStateLogDO toDo(TradeStateLog stateLog) {
        if (stateLog == null) {
            return null;
        }

        TradeStateLogDO stateLogDO = new TradeStateLogDO();
        BeanUtils.copyProperties(stateLog, stateLogDO);

        return stateLogDO;
    }

    public static TradeStateLog toModel(TradeStateLogDO stateLogDO) {
        if (stateLogDO == null) {
            return null;
        }

        TradeStateLog stateLog = new TradeStateLog();
        BeanUtils.copyProperties(stateLogDO, stateLog);

        return stateLog;
    }

    public static List<TradeStateLog> toModel(List<TradeStateLogDO> stateLogDOList) {
        return CollectionUtil.to(stateLogDOList, TradeStateLogConverter::toModel);
    }
}
