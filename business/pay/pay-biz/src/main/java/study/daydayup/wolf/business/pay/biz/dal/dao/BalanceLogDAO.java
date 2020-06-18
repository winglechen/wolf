package study.daydayup.wolf.business.pay.biz.dal.dao;

import study.daydayup.wolf.business.pay.biz.dal.dataobject.BalanceLogDO;

public interface BalanceLogDAO {
    int deleteById(Long id);

    int insert(BalanceLogDO record);

    int insertSelective(BalanceLogDO record);

    BalanceLogDO selectById(Long id);

    int updateByIdSelective(BalanceLogDO record);

    int updateById(BalanceLogDO record);
}