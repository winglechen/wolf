package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.BankCardDO;

public interface BankCardDAO {
    int deleteById(Long id);

    int insert(BankCardDO record);

    int insertSelective(BankCardDO record);

    BankCardDO selectById(Long id);

    int updateByIdSelective(BankCardDO record);

    int updateById(BankCardDO record);
}