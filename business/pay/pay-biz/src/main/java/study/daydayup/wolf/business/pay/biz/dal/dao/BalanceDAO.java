package study.daydayup.wolf.business.pay.biz.dal.dao;

import study.daydayup.wolf.business.pay.biz.dal.dataobject.BalanceDO;

public interface BalanceDAO {
    int deleteById(Long id);

    int insert(BalanceDO record);

    int insertSelective(BalanceDO record);

    BalanceDO selectById(Long id);

    int updateByIdSelective(BalanceDO record);

    int updateById(BalanceDO record);
}