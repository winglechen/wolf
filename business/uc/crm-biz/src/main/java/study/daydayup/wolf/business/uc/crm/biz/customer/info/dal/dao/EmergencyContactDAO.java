package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.EmergencyContactDO;

public interface EmergencyContactDAO {
    int deleteById(Long id);

    int insert(EmergencyContactDO record);

    int insertSelective(EmergencyContactDO record);

    EmergencyContactDO selectById(Long id);

    int updateByIdSelective(EmergencyContactDO record);

    int updateById(EmergencyContactDO record);
}