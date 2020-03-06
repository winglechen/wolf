package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.AadhaarDO;

public interface AadhaarDAO {
    int deleteById(Long id);

    int insert(AadhaarDO record);

    int insertSelective(AadhaarDO record);

    AadhaarDO selectById(Long id);

    int updateByIdSelective(AadhaarDO record);

    int updateById(AadhaarDO record);
}