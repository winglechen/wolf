package study.daydayup.wolf.bigdata.datav.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.bigdata.datav.api.dto.track.RepayRequest;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.TrackRepayDO;

public interface TrackRepayDAO {
    int deleteById(Long id);

    int insert(TrackRepayDO record);

    int insertSelective(TrackRepayDO record);

    TrackRepayDO selectById(Long id);

    int updateByIdSelective(TrackRepayDO record);

    int updateById(TrackRepayDO record);

    List<TrackRepayDO> selectByTrack(@Param("request") RepayRequest request);
}