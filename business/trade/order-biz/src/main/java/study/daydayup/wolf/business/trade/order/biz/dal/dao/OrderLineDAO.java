package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderLineDO;

@Mapper
public interface OrderLineDAO {
    int insert(OrderLineDO record);

    int insertSelective(OrderLineDO record);

    OrderLineDO selectById(Long id);

    int updateByIdSelective(OrderLineDO record);

    int updateById(OrderLineDO record);
}