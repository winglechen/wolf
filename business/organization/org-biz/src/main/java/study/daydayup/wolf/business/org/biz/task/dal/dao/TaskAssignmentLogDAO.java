package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskAssignmentLogDO;

public interface TaskAssignmentLogDAO {
    int deleteById(Long id);

    int insert(TaskAssignmentLogDO record);

    int insertSelective(TaskAssignmentLogDO record);

    TaskAssignmentLogDO selectById(Long id);

    int updateByIdSelective(TaskAssignmentLogDO record);

    int updateById(TaskAssignmentLogDO record);
}