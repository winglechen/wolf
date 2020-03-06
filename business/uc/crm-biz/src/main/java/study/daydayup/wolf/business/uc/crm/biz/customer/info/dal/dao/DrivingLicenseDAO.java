package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.DrivingLicenseDO;

public interface DrivingLicenseDAO {
    int deleteById(Long id);

    int insert(DrivingLicenseDO record);

    int insertSelective(DrivingLicenseDO record);

    DrivingLicenseDO selectById(Long id);

    int updateByIdSelective(DrivingLicenseDO record);

    int updateById(DrivingLicenseDO record);
}