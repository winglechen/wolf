package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyLoanDO;

public interface DailyLoanDAO {
    int deleteById(Long id);

    int insert(DailyLoanDO record);

    int insertSelective(DailyLoanDO record);

    DailyLoanDO selectById(Long id);

    int updateByIdSelective(DailyLoanDO record);

    int updateById(DailyLoanDO record);
}