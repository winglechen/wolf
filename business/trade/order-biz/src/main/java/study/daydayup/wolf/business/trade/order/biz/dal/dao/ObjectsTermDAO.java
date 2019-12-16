package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ObjectsTermDO;

@Mapper
public interface ObjectsTermDAO {
    int insert(ObjectsTermDO record);

    int insertSelective(ObjectsTermDO record);

    ObjectsTermDO selectById(Long id);

    int updateByIdSelective(ObjectsTermDO record);

    int updateById(ObjectsTermDO record);
}