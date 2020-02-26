package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyKoiDO;

public interface DailyKoiDAO {
    int deleteById(Long id);

    int insert(DailyKoiDO record);

    int insertSelective(DailyKoiDO record);

    DailyKoiDO selectById(Long id);

    int updateByIdSelective(DailyKoiDO record);

    int updateById(DailyKoiDO record);
}