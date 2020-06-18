package study.daydayup.wolf.business.account.biz.dal.dao;
import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Param;

import study.daydayup.wolf.business.account.biz.dal.dataobject.AccessTokenDO;

public interface AccessTokenDAO {
    int deleteById(Long id);

    int insert(AccessTokenDO record);

    int insertSelective(AccessTokenDO record);

    AccessTokenDO selectById(Long id);

    int updateByIdSelective(AccessTokenDO record);

    int updateById(AccessTokenDO record);

    AccessTokenDO selectByAccessToken(@Param("accessToken")String accessToken);

    int updateExpiredAtByRefreshToken(@Param("refreshToken")String refreshToken,@Param("updatedExpiredAt")LocalDateTime updatedExpiredAt,@Param("updatedAt") LocalDateTime updatedAt);

    int updateExpiredAtByAccountId(@Param("accountId")Long accountId, @Param("updatedExpiredAt")LocalDateTime updatedExpiredAt,@Param("updatedAt") LocalDateTime updatedAt);

    int updateExpiredAtById(@Param("id")Long id, @Param("updatedExpiredAt")LocalDateTime updatedExpiredAt, @Param("updatedAt") LocalDateTime updatedAt);

    int updateExpiredAtByAccessToken(@Param("accessToken")String accessToken, @Param("updatedExpiredAt") LocalDateTime updatedExpiredAt);

    int updateScopeByAccessToken(@Param("accessToken")String accessToken, @Param("scope")String scope);

    Long selectIdByAccessToken(@Param("accessToken")String accessToken);

}