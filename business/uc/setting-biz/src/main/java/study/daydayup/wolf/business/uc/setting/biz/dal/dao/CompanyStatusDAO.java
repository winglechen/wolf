package study.daydayup.wolf.business.uc.setting.biz.dal.dao;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CompanyStatusDO;

public interface CompanyStatusDAO {
    int deleteById(Long id);

    int insert(CompanyStatusDO record);

    int insertSelective(CompanyStatusDO record);

    CompanyStatusDO selectById(Long id);

    int updateByIdSelective(CompanyStatusDO record);

    int updateById(CompanyStatusDO record);
}