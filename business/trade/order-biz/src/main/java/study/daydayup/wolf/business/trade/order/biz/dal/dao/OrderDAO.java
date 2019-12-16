package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderDO;

@Mapper
public interface OrderDAO {
    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectById(Long id);

    int updateByIdSelective(OrderDO record);

    int updateById(OrderDO record);
}