package study.daydayup.wolf.business.org.biz.task.dal.dao;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskContactDO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;

import java.util.Collection;
import java.util.List;

public interface TaskContactDAO {
    int deleteById(Long id);

    int insert(TaskContactDO record);

    int insertSelective(TaskContactDO record);

    TaskContactDO selectById(Long id);

    int updateByIdSelective(TaskContactDO record);

    int updateById(TaskContactDO record);

    TaskContactDO selectByTaskId(@Param("taskId")Long taskId, @Param("orgId")Long orgId);

    List<TaskContactDO> selectByTaskIdIn(@Param("taskIdCollection") Collection<Long> taskIdCollection, @Param("orgId")Long orgId);

}