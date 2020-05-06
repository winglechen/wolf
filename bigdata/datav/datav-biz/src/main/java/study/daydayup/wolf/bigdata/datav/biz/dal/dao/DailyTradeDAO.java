package study.daydayup.wolf.bigdata.datav.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.TradeDateRequest;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyTradeDO;

public interface DailyTradeDAO {
    int deleteById(Long id);

    int insert(DailyTradeDO record);

    int insertSelective(DailyTradeDO record);

    DailyTradeDO selectById(Long id);

    int updateByIdSelective(DailyTradeDO record);

    int updateById(DailyTradeDO record);

    List<DailyTradeDO> selectByDateIn(@Param("request") TradeDateRequest request);

    List<DailyTradeDO> selectByDate(@Param("request") DateRangeRequest request);


}