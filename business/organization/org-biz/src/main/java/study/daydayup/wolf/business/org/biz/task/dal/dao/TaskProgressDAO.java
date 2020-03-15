package study.daydayup.wolf.business.org.biz.task.dal.dao;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskProgressDO;

import java.util.Collection;
import java.util.List;

public interface TaskProgressDAO {
    int deleteById(Long id);

    int insert(TaskProgressDO record);

    int insertSelective(TaskProgressDO record);

    TaskProgressDO selectById(Long id);

    int updateByIdSelective(TaskProgressDO record);

    int updateById(TaskProgressDO record);

    TaskProgressDO selectByTaskId(@Param("taskId")Long taskId, @Param("orgId")Long orgId);

    List<TaskProgressDO> selectByTaskIdIn(@Param("taskIdCollection") Collection<Long> taskIdCollection, @Param("orgId")Long orgId);

}