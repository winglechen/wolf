package study.daydayup.wolf.mq.broker.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.mq.broker.dal.dataobject.ScheduledMessageDO;

@Mapper
public interface ScheduledMessageAutoDAO {
    int insert(ScheduledMessageDO record);

    int insertSelective(ScheduledMessageDO record);

    ScheduledMessageDO selectById(Integer id);

    int updateByIdSelective(ScheduledMessageDO record);

    int updateById(ScheduledMessageDO record);
}