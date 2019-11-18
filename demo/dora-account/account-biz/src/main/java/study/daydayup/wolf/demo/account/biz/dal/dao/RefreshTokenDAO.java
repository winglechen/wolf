package study.daydayup.wolf.demo.account.biz.dal.dao;

import study.daydayup.wolf.demo.account.biz.dal.dataobject.RefreshTokenDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface RefreshTokenDAO {

    int insert(RefreshTokenDO refreshTokenDO);

    RefreshTokenDO getByRefreshTokenValue(@Param("refreshTokenValue") String refreshTokenValue);

    int deleteByRefreshTokenValue(@Param("refreshTokenValue") String refreshTokenValue);

    int updateExpiredAtByRefreshTokenValue(@Param("updatedExpiredAt") Date updatedExpiredAt, @Param("refreshTokenValue") String refreshTokenValue);



}