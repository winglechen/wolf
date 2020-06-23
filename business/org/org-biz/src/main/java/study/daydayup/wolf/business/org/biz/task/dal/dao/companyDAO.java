package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.companyDO;

public interface companyDAO {
    int deleteById(Long orgId);

    int insert(companyDO record);

    int insertSelective(companyDO record);

    companyDO selectById(Long orgId);

    int updateByIdSelective(companyDO record);

    int updateById(companyDO record);
}