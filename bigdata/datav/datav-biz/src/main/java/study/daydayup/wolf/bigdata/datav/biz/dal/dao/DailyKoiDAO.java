package study.daydayup.wolf.bigdata.datav.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyKoiDO;

public interface DailyKoiDAO {
    int deleteById(Long id);

    int insert(DailyKoiDO record);

    int insertSelective(DailyKoiDO record);

    DailyKoiDO selectById(Long id);

    int updateByIdSelective(DailyKoiDO record);

    int updateById(DailyKoiDO record);


    List<DailyKoiDO> selectByDate(@Param("request") DateRangeRequest request);




}