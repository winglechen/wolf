package study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLineDO;

public interface CreditLineDAO {
    int deleteById(Long id);

    int insert(CreditLineDO record);

    int insertSelective(CreditLineDO record);

    CreditLineDO selectById(Long id);

    int updateByIdSelective(CreditLineDO record);

    int updateById(CreditLineDO record);
}