package study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLogDO;

public interface CreditLogDAO {
    int deleteById(Long id);

    int insert(CreditLogDO record);

    int insertSelective(CreditLogDO record);

    CreditLogDO selectById(Long id);

    int updateByIdSelective(CreditLogDO record);

    int updateById(CreditLogDO record);
}