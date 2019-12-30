package study.daydayup.wolf.business.trade.order.biz.dal.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ContractDO;

@Mapper
public interface ContractDAO {
    Long insert(ContractDO record);

    Long insertSelective(ContractDO record);

    ContractDO selectById(Long id);

    int updateByIdSelective(ContractDO record);

    int updateById(ContractDO record);

    int updateByTradeNo(@Param("updated")ContractDO updated,@Param("key")ContractDO key);

    ContractDO selectByTradeNo(@Param("tradeNo")String tradeNo, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);



}