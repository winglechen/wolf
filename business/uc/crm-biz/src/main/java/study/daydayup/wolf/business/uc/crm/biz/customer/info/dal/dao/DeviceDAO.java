package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.DeviceDO;

public interface DeviceDAO {
    int deleteById(Long id);

    int insert(DeviceDO record);

    int insertSelective(DeviceDO record);

    DeviceDO selectById(Long id);

    int updateByIdSelective(DeviceDO record);

    int updateById(DeviceDO record);
}