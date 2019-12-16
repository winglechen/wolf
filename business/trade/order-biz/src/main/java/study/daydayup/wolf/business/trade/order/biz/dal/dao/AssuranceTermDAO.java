package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.AssuranceTermDO;

@Mapper
public interface AssuranceTermDAO {
    int insert(AssuranceTermDO record);

    int insertSelective(AssuranceTermDO record);

    AssuranceTermDO selectById(Long id);

    int updateByIdSelective(AssuranceTermDO record);

    int updateById(AssuranceTermDO record);
}