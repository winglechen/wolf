package study.daydayup.wolf.business.trade.order.biz.dal.dao;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.TypeRequest;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderDO;

@Mapper
public interface OrderDAO {
    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectById(Long id);

    int updateByIdSelective(OrderDO record);

    int updateById(OrderDO record);

    int updateByKey(@Param("key")OrderDO key, @Param("updated")OrderDO updated);

    List<OrderDO> selectRelatedTrade(@Param("key")OrderDO key);

    OrderDO selectByTradeNo(@Param("tradeNo")String tradeNo, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);

    List<OrderDO> selectByTradeNoIn(@Param("tradeNoCollection")Collection<String> tradeNoCollection, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);

    OrderDO selectLatestByBuyer(@Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);

    List<OrderDO> selectByBuyerId(@Param("buyerId")Long buyerId);

    List<OrderDO> selectBySellerId(@Param("sellerId")Long sellerId);

    List<OrderDO> sellerByTradeType(@Param("query") TypeRequest request);

    List<OrderDO> sellerByState(@Param("query") StateRequest request);

    List<OrderDO> sellerByBuyerId(@Param("query") BuyerRequest request);

    List<OrderDO> selectByRelatedTradeNo(@Param("relatedTradeNo")String relatedTradeNo, @Param("sellerId")Long sellerId);




}