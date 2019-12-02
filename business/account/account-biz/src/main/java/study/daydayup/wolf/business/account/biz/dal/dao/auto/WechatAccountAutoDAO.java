package study.daydayup.wolf.business.account.biz.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.account.biz.dal.dataobject.WechatAccountDO;

@Mapper
public interface WechatAccountAutoDAO {
    int deleteById(Long id);

    int insert(WechatAccountDO record);

    int insertSelective(WechatAccountDO record);

    WechatAccountDO selectById(Long id);

    int updateByIdSelective(WechatAccountDO record);

    int updateById(WechatAccountDO record);
}