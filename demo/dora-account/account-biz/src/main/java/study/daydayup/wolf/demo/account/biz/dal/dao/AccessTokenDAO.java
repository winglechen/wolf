package study.daydayup.wolf.demo.account.biz.dal.dao;

import study.daydayup.wolf.demo.account.biz.dal.dataobject.AccessTokenDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface AccessTokenDAO {

    int insert(AccessTokenDO accessTokenDO);

    int deleteByAccessTokenValue(@Param("accessTokenValue") String accessTokenValue);

    AccessTokenDO getById(Long id);

    AccessTokenDO getByAccessTokenValue(@Param("accessTokenValue") String accessTokenValue);

    AccessTokenDO getByUidAndClientId(@Param("uid") Long uid, @Param("clientId") String clientId);

    AccessTokenDO getByRefreshTokenValueAndUidAndClientId(@Param("refreshTokenValue") String refreshTokenValue, @Param("uid") Long uid, @Param("clientId") String clientId);

    int updateExpiredAtByAccessTokenValue(@Param("updatedExpiredAt") Date updatedExpiredAt, @Param("accessTokenValue") String accessTokenValue);



}