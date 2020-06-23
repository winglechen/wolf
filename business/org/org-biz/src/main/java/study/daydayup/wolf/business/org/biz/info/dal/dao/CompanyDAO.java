package study.daydayup.wolf.business.org.biz.info.dal.dao;

import study.daydayup.wolf.business.org.biz.info.dal.dataobject.CompanyDO;

public interface CompanyDAO {
    int deleteById(Long orgId);

    int insert(CompanyDO record);

    int insertSelective(CompanyDO record);

    CompanyDO selectById(Long orgId);

    int updateByIdSelective(CompanyDO record);

    int updateById(CompanyDO record);
}