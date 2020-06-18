package study.daydayup.wolf.business.pay.biz.dal.dao;

import study.daydayup.wolf.business.pay.biz.dal.dataobject.TransactionDO;

public interface TransactionDAO {
    int deleteById(Long id);

    int insert(TransactionDO record);

    int insertSelective(TransactionDO record);

    TransactionDO selectById(Long id);

    int updateByIdSelective(TransactionDO record);

    int updateById(TransactionDO record);
}