package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.PriceChangeLogDO;

@Mapper
public interface PriceChangeLogDAO {
    int insert(PriceChangeLogDO record);

    int insertSelective(PriceChangeLogDO record);

    PriceChangeLogDO selectById(Integer id);

    int updateByIdSelective(PriceChangeLogDO record);

    int updateById(PriceChangeLogDO record);
}