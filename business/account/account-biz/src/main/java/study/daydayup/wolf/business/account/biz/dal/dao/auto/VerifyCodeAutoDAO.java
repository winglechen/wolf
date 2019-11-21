package study.daydayup.wolf.business.account.biz.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.account.biz.dal.dataobject.VerifyCodeDO;

@Mapper
public interface VerifyCodeAutoDAO {
    int insert(VerifyCodeDO record);

    int insertSelective(VerifyCodeDO record);

    VerifyCodeDO selectById(Long id);

    int updateByIdSelective(VerifyCodeDO record);

    int updateById(VerifyCodeDO record);
}