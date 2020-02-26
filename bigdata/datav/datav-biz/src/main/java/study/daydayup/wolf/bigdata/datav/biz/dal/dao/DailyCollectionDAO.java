package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyCollectionDO;

public interface DailyCollectionDAO {
    int deleteById(Long id);

    int insert(DailyCollectionDO record);

    int insertSelective(DailyCollectionDO record);

    DailyCollectionDO selectById(Long id);

    int updateByIdSelective(DailyCollectionDO record);

    int updateById(DailyCollectionDO record);
}