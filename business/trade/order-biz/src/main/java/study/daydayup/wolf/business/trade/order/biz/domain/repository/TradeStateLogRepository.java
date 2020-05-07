package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.entity.trade.TradeStateLog;
import study.daydayup.wolf.business.trade.order.biz.converter.TradeStateLogConverter;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.TradeStateLogDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.TradeStateLogDO;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2020/5/7 5:54 下午
 **/
@Component
public class TradeStateLogRepository extends AbstractRepository implements Repository {
    @Resource
    private TradeStateLogDAO logDAO;

    public int log(Order order) {
        if (null == order.getState() || null == order.getStateLog()) {
            return 0;
        }

        TradeStateLog log = order.getStateLog();
        log.setTargetState(order.getState().getCode());

        return saveLog(log);
    }

    public int log(Contract contract) {
        if (null == contract.getState() || null == contract.getStateLog()) {
            return 0;
        }

        TradeStateLog log = contract.getStateLog();
        log.setTargetState(contract.getState().getCode());

        return saveLog(log);
    }

    public int saveLog(@NonNull TradeStateLog log) {
        TradeStateLogDO logDO = TradeStateLogConverter.toDo(log);
        if (logDO == null) {
            return 0;
        }

        return logDAO.insertSelective(logDO);
    }

}
