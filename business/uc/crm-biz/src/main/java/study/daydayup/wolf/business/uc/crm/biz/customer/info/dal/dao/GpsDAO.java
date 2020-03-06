package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.GpsDO;

public interface GpsDAO {
    int deleteById(Long id);

    int insert(GpsDO record);

    int insertSelective(GpsDO record);

    GpsDO selectById(Long id);

    int updateByIdSelective(GpsDO record);

    int updateById(GpsDO record);
}