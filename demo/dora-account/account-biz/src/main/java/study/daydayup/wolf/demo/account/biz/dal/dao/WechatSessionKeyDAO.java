package study.daydayup.wolf.demo.account.biz.dal.dao;

import study.daydayup.wolf.demo.account.biz.dal.dataobject.WechatSessionKeyDO;
import org.apache.ibatis.annotations.Param;

public interface WechatSessionKeyDAO {

    int insert(WechatSessionKeyDO record);

    WechatSessionKeyDO getByUidAndSessionKey(@Param("uid")Long uid,@Param("sessionKey")String sessionKey);

    WechatSessionKeyDO getOneByUid(@Param("uid")Long uid);


}