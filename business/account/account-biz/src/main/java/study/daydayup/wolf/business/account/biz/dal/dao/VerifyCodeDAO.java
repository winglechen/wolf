package study.daydayup.wolf.business.account.biz.dal.dao;

import study.daydayup.wolf.business.account.biz.dal.dataobject.VerifyCodeDO;

public interface VerifyCodeDAO {
    int deleteById(Long id);

    int insert(VerifyCodeDO record);

    int insertSelective(VerifyCodeDO record);

    VerifyCodeDO selectById(Long id);

    int updateByIdSelective(VerifyCodeDO record);

    int updateById(VerifyCodeDO record);
}