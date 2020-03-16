package study.daydayup.wolf.business.org.biz.task.dal.dao;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;

public interface TaskDetailDAO {
    int deleteById(Long id);

    int insert(TaskDetailDO record);

    int insertSelective(TaskDetailDO record);

    TaskDetailDO selectById(Long id);

    int updateByIdSelective(TaskDetailDO record);

    int updateById(TaskDetailDO record);

    TaskDetailDO selectByTaskId(@Param("taskId")Long taskId, @Param("orgId")Long orgId);

    List<TaskDetailDO> selectByTaskIdIn(@Param("taskIdCollection")Collection<Long> taskIdCollection, @Param("orgId")Long orgId);

    int updateByKey(@Param("updated") TaskDetailDO updated, @Param("key") TaskDetailDO key );

}