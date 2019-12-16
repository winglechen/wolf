package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.TradeStateLogDO;

@Mapper
public interface TradeStateLogDAO {
    int insert(TradeStateLogDO record);

    int insertSelective(TradeStateLogDO record);

    TradeStateLogDO selectById(Integer id);

    int updateByIdSelective(TradeStateLogDO record);

    int updateById(TradeStateLogDO record);
}