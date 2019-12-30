package study.daydayup.wolf.business.trade.order.biz.dal.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderDO;

@Mapper
public interface OrderDAO {
    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectById(Long id);

    int updateByIdSelective(OrderDO record);

    int updateById(OrderDO record);

    int updateByKey(@Param("key")OrderDO key, @Param("updated")OrderDO updated);

    List<OrderDO> selectRelatedTrade(@Param("key")OrderDO key);



}