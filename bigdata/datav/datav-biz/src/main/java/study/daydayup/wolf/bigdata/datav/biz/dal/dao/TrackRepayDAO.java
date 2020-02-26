package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.TrackRepayDO;

public interface TrackRepayDAO {
    int deleteById(Long id);

    int insert(TrackRepayDO record);

    int insertSelective(TrackRepayDO record);

    TrackRepayDO selectById(Long id);

    int updateByIdSelective(TrackRepayDO record);

    int updateById(TrackRepayDO record);
}