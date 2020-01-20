package study.daydayup.wolf.business.trade.order.biz.dal.dao;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.TypeRequest;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ContractDO;

@Mapper
public interface ContractDAO {
    Long insert(ContractDO record);

    Long insertSelective(ContractDO record);

    ContractDO selectById(Long id);

    int updateByIdSelective(ContractDO record);

    int updateById(ContractDO record);

    int updateByKey(@Param("updated")ContractDO updated, @Param("key")ContractDO key);

    ContractDO selectByTradeNo(@Param("tradeNo")String tradeNo, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);

    List<ContractDO> selectByTradeNoIn(@Param("tradeNoCollection")Collection<String> tradeNoCollection, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);

    ContractDO selectLatestByBuyer(@Param("buyerId")Long buyerId);

    List<ContractDO> selectByBuyerId(@Param("buyerId")Long buyerId);

    List<ContractDO> selectBySellerId(@Param("sellerId")Long sellerId);

    List<ContractDO> sellerByTradeType(@Param("query")TypeRequest request);

    List<ContractDO> sellerByState(@Param("query") StateRequest request);

    List<ContractDO> sellerByBuyerId(@Param("query") BuyerRequest request);




}