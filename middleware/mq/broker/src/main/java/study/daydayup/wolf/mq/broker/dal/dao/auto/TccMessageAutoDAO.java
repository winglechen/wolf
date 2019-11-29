package study.daydayup.wolf.mq.broker.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.mq.broker.dal.dataobject.TccMessageDO;

@Mapper
public interface TccMessageAutoDAO {
    int insert(TccMessageDO record);

    int insertSelective(TccMessageDO record);

    TccMessageDO selectById(Integer id);

    int updateByIdSelective(TccMessageDO record);

    int updateById(TccMessageDO record);
}