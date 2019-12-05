package study.daydayup.wolf.business.account.biz.dal.dao;

import study.daydayup.wolf.business.account.biz.dal.dataobject.OrganizationDO;

public interface OrganizationDAO {
    int deleteById(Long id);

    int insert(OrganizationDO record);

    int insertSelective(OrganizationDO record);

    OrganizationDO selectById(Long id);

    int updateByIdSelective(OrganizationDO record);

    int updateById(OrganizationDO record);
}