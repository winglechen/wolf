package study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditConfigDO;

public interface CreditConfigDAO {
    int deleteById(Long id);

    int insert(CreditConfigDO record);

    int insertSelective(CreditConfigDO record);

    CreditConfigDO selectById(Long id);

    int updateByIdSelective(CreditConfigDO record);

    int updateById(CreditConfigDO record);
}