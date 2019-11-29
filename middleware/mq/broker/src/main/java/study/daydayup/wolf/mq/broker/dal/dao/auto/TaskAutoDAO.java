package study.daydayup.wolf.mq.broker.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.mq.broker.dal.dataobject.TaskDO;

@Mapper
public interface TaskAutoDAO {
    int insert(TaskDO record);

    int insertSelective(TaskDO record);

    TaskDO selectById(Long id);

    int updateByIdSelective(TaskDO record);

    int updateById(TaskDO record);
}