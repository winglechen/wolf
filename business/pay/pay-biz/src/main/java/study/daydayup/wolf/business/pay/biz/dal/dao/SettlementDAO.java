package study.daydayup.wolf.business.pay.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.pay.api.dto.base.manage.SettlementQuery;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.SettlementDO;

public interface SettlementDAO {
    int deleteById(Long id);

    int insert(SettlementDO record);

    int insertSelective(SettlementDO record);

    SettlementDO selectById(Long id);

    int updateByIdSelective(SettlementDO record);

    int updateById(SettlementDO record);

    SettlementDO selectBySettlementNoAndAccountId(@Param("settlementNo")String settlementNo,@Param("accountId")Long accountId);

    List<SettlementDO> selectByRange(@Param("query") SettlementQuery query);

}