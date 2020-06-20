package study.daydayup.wolf.business.account.biz.dal.dao;

import study.daydayup.wolf.business.account.biz.dal.dataobject.OpenAppDO;

public interface OpenAppDAO {
    int deleteById(Long id);

    int insert(OpenAppDO record);

    int insertSelective(OpenAppDO record);

    OpenAppDO selectById(Long id);

    int updateByIdSelective(OpenAppDO record);

    int updateById(OpenAppDO record);
}