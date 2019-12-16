package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.TradeMemoDO;

@Mapper
public interface TradeMemoDAO {
    int insert(TradeMemoDO record);

    int insertSelective(TradeMemoDO record);

    TradeMemoDO selectById(Long id);

    int updateByIdSelective(TradeMemoDO record);

    int updateById(TradeMemoDO record);
}