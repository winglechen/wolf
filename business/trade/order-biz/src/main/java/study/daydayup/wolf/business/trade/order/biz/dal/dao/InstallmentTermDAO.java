package study.daydayup.wolf.business.trade.order.biz.dal.dao;
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


}