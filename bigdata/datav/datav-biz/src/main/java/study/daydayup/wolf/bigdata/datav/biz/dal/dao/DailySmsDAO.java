package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailySmsDO;

import java.util.List;

public interface DailySmsDAO {
    int deleteById(Long id);

    int insert(DailySmsDO record);

    int insertSelective(DailySmsDO record);

    DailySmsDO selectById(Long id);

    int updateByIdSelective(DailySmsDO record);

    int updateById(DailySmsDO record);

    List<DailySmsDO> selectByDate(@Param("request") DateRangeRequest request);

}