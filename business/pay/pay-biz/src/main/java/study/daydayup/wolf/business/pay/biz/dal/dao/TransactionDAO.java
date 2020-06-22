package study.daydayup.wolf.business.pay.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.pay.api.dto.base.manage.TransactionQuery;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.TransactionDO;

public interface TransactionDAO {
    int deleteById(Long id);

    int insert(TransactionDO record);

    int insertSelective(TransactionDO record);

    TransactionDO selectById(Long id);

    int updateByIdSelective(TransactionDO record);

    int updateById(TransactionDO record);

    List<TransactionDO> selectByPaymentNoAndPayeeId(@Param("paymentNo")String paymentNo,@Param("payeeId")Long payeeId);

    List<TransactionDO> selectBySettlementNoAndPayeeId(@Param("settlementNo")String settlementNo, @Param("transactionType")Integer transactionType, @Param("payeeId")Long payeeId);

    List<TransactionDO> selectByRange(@Param("query") TransactionQuery query);

}