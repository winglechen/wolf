package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyTradeDO;

public interface DailyTradeDAO {
    int deleteById(Long id);

    int insert(DailyTradeDO record);

    int insertSelective(DailyTradeDO record);

    DailyTradeDO selectById(Long id);

    int updateByIdSelective(DailyTradeDO record);

    int updateById(DailyTradeDO record);
}