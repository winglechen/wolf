package study.daydayup.wolf.business.account.biz.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.account.biz.dal.dataobject.RefreshTokenDO;

@Mapper
public interface RefreshTokenAutoDAO {
    int insert(RefreshTokenDO record);

    int insertSelective(RefreshTokenDO record);

    RefreshTokenDO selectById(Long id);

    int updateByIdSelective(RefreshTokenDO record);

    int updateById(RefreshTokenDO record);
}