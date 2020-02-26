package study.daydayup.wolf.business.pay.biz.dal.dao;

import study.daydayup.wolf.business.pay.biz.dal.dataobject.TradeMergeDO;

public interface TradeMergeDAO {
    int deleteById(Long id);

    int insert(TradeMergeDO record);

    int insertSelective(TradeMergeDO record);

    TradeMergeDO selectById(Long id);

    int updateByIdSelective(TradeMergeDO record);

    int updateById(TradeMergeDO record);
}