package study.daydayup.wolf.business.account.biz.dal.dao;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.account.biz.dal.dataobject.AccessTokenDO;

public interface AccessTokenDAO {
    int deleteById(Long id);

    int insert(AccessTokenDO record);

    int insertSelective(AccessTokenDO record);

    AccessTokenDO selectById(Long id);

    int updateByIdSelective(AccessTokenDO record);

    int updateById(AccessTokenDO record);

    List<AccessTokenDO> selectByAccessToken(@Param("accessToken")String accessToken);

    int updateExpiredAtByRefreshToken(@Param("updatedExpiredAt")Date updatedExpiredAt,@Param("refreshToken")String refreshToken);

    int updateExpiredAtByAccountId(@Param("updatedExpiredAt")Date updatedExpiredAt,@Param("accountId")Long accountId);



}