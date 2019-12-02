package study.daydayup.wolf.business.account.biz.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccessTokenDO;

@Mapper
public interface AccessTokenAutoDAO {
    int deleteById(Long id);

    int insert(AccessTokenDO record);

    int insertSelective(AccessTokenDO record);

    AccessTokenDO selectById(Long id);

    int updateByIdSelective(AccessTokenDO record);

    int updateById(AccessTokenDO record);
}