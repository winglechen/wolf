package study.daydayup.wolf.business.pay.biz.dal.dao;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentQuery;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.PaymentDO;

public interface PaymentDAO {
    int deleteById(Long id);

    int insert(PaymentDO record);

    int insertSelective(PaymentDO record);

    PaymentDO selectById(Long id);

    int updateByIdSelective(PaymentDO record);

    int updateById(PaymentDO record);

    int updateByPaymentNo(@Param("updated")PaymentDO updated, @Param("paymentNo")String paymentNo);

    List<PaymentDO> selectByTradeNo(@Param("tradeNo")String tradeNo, @Param("state")Integer state);

    PaymentDO selectByPaymentNo(@Param("paymentNo")String paymentNo);

    PaymentDO selectByPaymentNoAndPayeeId(@Param("paymentNo")String paymentNo,@Param("payeeId")Long payeeId);

    List<PaymentDO> selectByOutTradeNo(@Param("outTradeNo")String outTradeNo, @Param("payeeId")Long payeeId);

    List<PaymentDO> selectByPayeeId(@Param("payeeId")Long payeeId, @Param("state")Integer state);

    List<PaymentDO> selectByRange(@Param("query") PaymentQuery query);





}