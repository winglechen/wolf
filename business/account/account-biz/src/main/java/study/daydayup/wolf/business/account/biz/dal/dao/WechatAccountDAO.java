package study.daydayup.wolf.business.account.biz.dal.dao;

import study.daydayup.wolf.business.account.biz.dal.dataobject.WechatAccountDO;

public interface WechatAccountDAO {
    int deleteById(Long id);

    int insert(WechatAccountDO record);

    int insertSelective(WechatAccountDO record);

    WechatAccountDO selectById(Long id);

    int updateByIdSelective(WechatAccountDO record);

    int updateById(WechatAccountDO record);
}