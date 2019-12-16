package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.PaymentTermDO;

@Mapper
public interface PaymentTermDAO {
    int insert(PaymentTermDO record);

    int insertSelective(PaymentTermDO record);

    PaymentTermDO selectById(Long id);

    int updateByIdSelective(PaymentTermDO record);

    int updateById(PaymentTermDO record);
}