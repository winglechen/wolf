package study.daydayup.wolf.business.pay.biz.dal.dao;

import study.daydayup.wolf.business.pay.biz.dal.dataobject.SettlementDO;

public interface SettlementDAO {
    int deleteById(Long id);

    int insert(SettlementDO record);

    int insertSelective(SettlementDO record);

    SettlementDO selectById(Long id);

    int updateByIdSelective(SettlementDO record);

    int updateById(SettlementDO record);
}