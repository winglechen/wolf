package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.LoanTermDO;

@Mapper
public interface LoanTermDAO {
    int insert(LoanTermDO record);

    int insertSelective(LoanTermDO record);

    LoanTermDO selectById(Long id);

    int updateByIdSelective(LoanTermDO record);

    int updateById(LoanTermDO record);
}