package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderAddressDO;

@Mapper
public interface OrderAddressDAO {
    int insert(OrderAddressDO record);

    int insertSelective(OrderAddressDO record);

    OrderAddressDO selectById(Long id);

    int updateByIdSelective(OrderAddressDO record);

    int updateById(OrderAddressDO record);
}