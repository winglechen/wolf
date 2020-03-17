package study.daydayup.wolf.business.org.biz.task.dal.dao;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskStateLogDO;

import java.util.Collection;
import java.util.List;

public interface TaskStateLogDAO {
    int deleteById(Long id);

    int insert(TaskStateLogDO record);

    int insertSelective(TaskStateLogDO record);

    TaskStateLogDO selectById(Long id);

    int updateByIdSelective(TaskStateLogDO record);

    int updateById(TaskStateLogDO record);

    TaskStateLogDO selectByTaskId(@Param("taskId")Long taskId, @Param("orgId")Long orgId);

    List<TaskStateLogDO> selectByTaskIdIn(@Param("taskIdCollection") Collection<Long> taskIdCollection, @Param("orgId")Long orgId);

}