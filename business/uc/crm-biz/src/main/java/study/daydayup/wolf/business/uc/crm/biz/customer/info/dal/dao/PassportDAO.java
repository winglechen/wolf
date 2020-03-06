package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.PassportDO;

public interface PassportDAO {
    int deleteById(Long id);

    int insert(PassportDO record);

    int insertSelective(PassportDO record);

    PassportDO selectById(Long id);

    int updateByIdSelective(PassportDO record);

    int updateById(PassportDO record);
}