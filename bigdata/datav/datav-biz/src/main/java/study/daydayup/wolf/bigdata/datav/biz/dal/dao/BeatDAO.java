package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.BeatDO;

public interface BeatDAO {
    int deleteById(Long id);

    int insert(BeatDO record);

    int insertSelective(BeatDO record);

    BeatDO selectById(Long id);

    int updateByIdSelective(BeatDO record);

    int updateById(BeatDO record);
}