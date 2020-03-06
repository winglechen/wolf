package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.LivenessAuthInfoDO;

public interface LivenessAuthInfoDAO {
    int deleteById(Long id);

    int insert(LivenessAuthInfoDO record);

    int insertSelective(LivenessAuthInfoDO record);

    LivenessAuthInfoDO selectById(Long id);

    int updateByIdSelective(LivenessAuthInfoDO record);

    int updateByPrimaryKeyWithBLOBs(LivenessAuthInfoDO record);

    int updateById(LivenessAuthInfoDO record);
}