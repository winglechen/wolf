package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.VoterCardDO;

public interface VoterCardDAO {
    int deleteById(Long id);

    int insert(VoterCardDO record);

    int insertSelective(VoterCardDO record);

    VoterCardDO selectById(Long id);

    int updateByIdSelective(VoterCardDO record);

    int updateById(VoterCardDO record);
}