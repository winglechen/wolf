package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.AppInstalledDO;

public interface AppInstalledDAO {
    int deleteById(Long id);

    int insert(AppInstalledDO record);

    int insertSelective(AppInstalledDO record);

    AppInstalledDO selectById(Long id);

    int updateByIdSelective(AppInstalledDO record);

    int updateById(AppInstalledDO record);
}