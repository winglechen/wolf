package study.daydayup.wolf.demo.account.biz.dal.dao;

import study.daydayup.wolf.demo.account.biz.dal.dataobject.VerifyCodeDO;
import org.apache.ibatis.annotations.Param;

public interface VerifyCodeDAO {

    int insert(VerifyCodeDO record);

    VerifyCodeDO getByMobileAndCode(@Param("mobile")String mobile, @Param("code")String code);

    VerifyCodeDO getByMobile(@Param("mobile")String mobile);
}