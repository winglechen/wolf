package study.daydayup.wolf.business.pay.biz.dal.dao;

import study.daydayup.wolf.business.pay.biz.dal.dataobject.RazorpayAccountDO;

public interface RazorpayAccountDAO {
    int deleteById(Long id);

    int insert(RazorpayAccountDO record);

    int insertSelective(RazorpayAccountDO record);

    RazorpayAccountDO selectById(Long id);

    int updateByIdSelective(RazorpayAccountDO record);

    int updateById(RazorpayAccountDO record);
}