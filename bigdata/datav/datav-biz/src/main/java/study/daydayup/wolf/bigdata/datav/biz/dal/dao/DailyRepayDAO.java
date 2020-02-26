package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyRepayDO;

public interface DailyRepayDAO {
    int deleteById(Long id);

    int insert(DailyRepayDO record);

    int insertSelective(DailyRepayDO record);

    DailyRepayDO selectById(Long id);

    int updateByIdSelective(DailyRepayDO record);

    int updateById(DailyRepayDO record);
}