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

    AccessTokenDO selectByAccessToken(@Param("accessToken")String accessToken);

    int updateExpiredAtByRefreshToken(@Param("refreshToken")String refreshToken,@Param("updatedExpiredAt")Date updatedExpiredAt,@Param("updatedAt") Date updatedAt);

    int updateExpiredAtByAccountId(@Param("accountId")Long accountId, @Param("updatedExpiredAt")Date updatedExpiredAt,@Param("updatedAt") Date updatedAt);

    int updateExpiredAtById(@Param("id")Long id, @Param("updatedExpiredAt")Date updatedExpiredAt, @Param("updatedAt") Date updatedAt);

    Long selectIdByAccessToken(@Param("accessToken")String accessToken);

}