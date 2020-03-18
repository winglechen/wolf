package study.daydayup.wolf.business.trade.order.biz.dal.dao;
import java.time.LocalDate;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.InstallmentTermDO;

@Mapper
public interface InstallmentTermDAO {
    int insert(InstallmentTermDO record);

    int insertSelective(InstallmentTermDO record);

    InstallmentTermDO selectById(Long id);

    int updateByIdSelective(InstallmentTermDO record);

    int updateById(InstallmentTermDO record);

    List<InstallmentTermDO> selectByTradeNo(@Param("tradeNo")String tradeNo, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);

    int updateByTradeNo(@Param("updated")InstallmentTermDO updated,@Param("key")InstallmentTermDO key);

    List<InstallmentTermDO> selectByTradeNoIn(@Param("tradeNoCollection")Collection<String> tradeNoCollection, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);

    List<InstallmentTermDO> selectByDueAt(@Param("dueAt")LocalDate dueAt);

    List<InstallmentTermDO> selectByDueAtAndBuyer(@Param("dueAt")LocalDate dueAt, @Param("buyerId")Long buyerId, @Param("sellerId")Long sellerId);

    List<InstallmentTermDO> selectByDueAtAndSeller(@Param("dueAt")LocalDate dueAt, @Param("sellerId")Long sellerId);

}