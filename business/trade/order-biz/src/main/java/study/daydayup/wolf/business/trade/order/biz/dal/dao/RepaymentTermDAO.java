package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.RepaymentTermDO;

@Mapper
public interface RepaymentTermDAO {
    int insert(RepaymentTermDO record);

    int insertSelective(RepaymentTermDO record);

    RepaymentTermDO selectById(Long id);

    int updateByIdSelective(RepaymentTermDO record);

    int updateById(RepaymentTermDO record);
}