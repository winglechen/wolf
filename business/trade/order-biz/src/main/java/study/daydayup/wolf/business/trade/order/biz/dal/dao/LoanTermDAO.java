package study.daydayup.wolf.business.trade.order.biz.dal.dao;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.LoanTermDO;

@Mapper
public interface LoanTermDAO {
    int insert(LoanTermDO record);

    int insertSelective(LoanTermDO record);

    LoanTermDO selectById(Long id);

    int updateByIdSelective(LoanTermDO record);

    int updateById(LoanTermDO record);

    LoanTermDO selectByTradeNo(@Param("tradeNo")String tradeNo, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);

    List<LoanTermDO> selectByTradeNoIn(@Param("tradeNoCollection")Collection<String> tradeNoCollection, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);





}