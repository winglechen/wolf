package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyCollectorDO;

public interface DailyCollectorDAO {
    int deleteById(Long id);

    int insert(DailyCollectorDO record);

    int insertSelective(DailyCollectorDO record);

    DailyCollectorDO selectById(Long id);

    int updateByIdSelective(DailyCollectorDO record);

    int updateById(DailyCollectorDO record);
}