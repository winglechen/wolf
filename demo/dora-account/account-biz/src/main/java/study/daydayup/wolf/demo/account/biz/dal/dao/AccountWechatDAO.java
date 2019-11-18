package study.daydayup.wolf.demo.account.biz.dal.dao;

import study.daydayup.wolf.demo.account.biz.dal.dataobject.AccountWechatDO;
import org.apache.ibatis.annotations.Param;

/**
* Created by Mybatis Generator 2019/09/22
*/
public interface AccountWechatDAO {

    int insert(AccountWechatDO record);

    AccountWechatDO selectOneByOpenId(@Param("openId") String openId);

    AccountWechatDO selectOneByUnionId(@Param("unionId") String unionId);

    AccountWechatDO selectOneByMpOpenId(@Param("mpOpenId")String mpOpenId);

    int updateUserIdByOpenId(@Param("updatedUid") Long updatedUid, @Param("openId") String openId);

    int updateOpenIdByUidAndUnionId(@Param("updatedOpenId") String updatedOpenId,@Param("uid")Long uid, @Param("unionId") String unionId);

    int updateMpOpenIdByUidAndUnionId(@Param("updatedMpOpenId") String updatedMpOpenId, @Param("uid") Long uid, @Param("unionId") String unionId);



}