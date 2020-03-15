package study.daydayup.wolf.business.org.biz.task.dal.dao;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskTradeDO;

import java.util.Collection;
import java.util.List;

public interface TaskTradeDAO {
    int deleteById(Long id);

    int insert(TaskTradeDO record);

    int insertSelective(TaskTradeDO record);

    TaskTradeDO selectById(Long id);

    int updateByIdSelective(TaskTradeDO record);

    int updateById(TaskTradeDO record);

    TaskTradeDO selectByTaskId(@Param("taskId")Long taskId, @Param("orgId")Long orgId);

    List<TaskTradeDO> selectByTaskIdIn(@Param("taskIdCollection") Collection<Long> taskIdCollection, @Param("orgId")Long orgId);


}