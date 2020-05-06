package study.daydayup.wolf.bigdata.datav.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.time.LocalDate;
import java.util.Collection;

import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyTradeDO;

public interface DailyTradeDAO {
    int deleteById(Long id);

    int insert(DailyTradeDO record);

    int insertSelective(DailyTradeDO record);

    DailyTradeDO selectById(Long id);

    int updateByIdSelective(DailyTradeDO record);

    int updateById(DailyTradeDO record);

    List<DailyTradeDO> selectByDateIn(@Param("dateList")Collection<LocalDate> dateList, @Param("orgId") Long orgId);

    List<DailyTradeDO> selectByDate(@Param("request") DateRangeRequest request);


}