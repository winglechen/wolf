package study.daydayup.wolf.business.pay.biz.dal.dao;

import study.daydayup.wolf.business.pay.biz.dal.dataobject.PaymentStateLogDO;

public interface PaymentStateLogDAO {
    int deleteById(Integer id);

    int insert(PaymentStateLogDO record);

    int insertSelective(PaymentStateLogDO record);

    PaymentStateLogDO selectById(Integer id);

    int updateByIdSelective(PaymentStateLogDO record);

    int updateById(PaymentStateLogDO record);
}