package study.daydayup.wolf.business.org.biz.task.dal.dao;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDOWithBLOBs;

public interface TaskDetailDAO {
    int deleteById(Long id);

    int insert(TaskDetailDOWithBLOBs record);

    int insertSelective(TaskDetailDOWithBLOBs record);

    TaskDetailDOWithBLOBs selectById(Long id);

    int updateByIdSelective(TaskDetailDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TaskDetailDOWithBLOBs record);

    int updateById(TaskDetailDO record);


    TaskDetailDO selectByTaskId(@Param("taskId")Long taskId, @Param("orgId")Long orgId);

    List<TaskDetailDO> selectByTaskIdIn(@Param("taskIdCollection")Collection<Long> taskIdCollection, @Param("orgId")Long orgId);



}