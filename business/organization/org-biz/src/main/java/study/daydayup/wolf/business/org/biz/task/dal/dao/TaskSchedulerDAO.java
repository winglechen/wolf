package study.daydayup.wolf.business.org.biz.task.dal.dao;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskSchedulerDO;

import java.util.Collection;
import java.util.List;

public interface TaskSchedulerDAO {
    int deleteById(Long id);

    int insert(TaskSchedulerDO record);

    int insertSelective(TaskSchedulerDO record);

    TaskSchedulerDO selectById(Long id);

    int updateByIdSelective(TaskSchedulerDO record);

    int updateById(TaskSchedulerDO record);

    TaskSchedulerDO selectByTaskId(@Param("taskId")Long taskId, @Param("orgId")Long orgId);

    List<TaskSchedulerDO> selectByTaskIdIn(@Param("taskIdCollection") Collection<Long> taskIdCollection, @Param("orgId")Long orgId);

}