package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ConsignTermDO;

@Mapper
public interface ConsignTermDAO {
    int insert(ConsignTermDO record);

    int insertSelective(ConsignTermDO record);

    ConsignTermDO selectById(Long id);

    int updateByIdSelective(ConsignTermDO record);

    int updateById(ConsignTermDO record);
}