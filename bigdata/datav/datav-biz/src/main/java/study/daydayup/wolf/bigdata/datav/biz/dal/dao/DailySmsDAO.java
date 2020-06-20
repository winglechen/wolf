package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailySmsDO;

public interface DailySmsDAO {
    int deleteById(Long id);

    int insert(DailySmsDO record);

    int insertSelective(DailySmsDO record);

    DailySmsDO selectById(Long id);

    int updateByIdSelective(DailySmsDO record);

    int updateById(DailySmsDO record);
}