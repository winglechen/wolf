package study.daydayup.wolf.business.goods.biz.dal.dao;

import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsLoanDO;

public interface GoodsLoanDAO {
    int insert(GoodsLoanDO record);

    int insertSelective(GoodsLoanDO record);

    GoodsLoanDO selectById(Long id);

    int updateByIdSelective(GoodsLoanDO record);

    int updateById(GoodsLoanDO record);
}