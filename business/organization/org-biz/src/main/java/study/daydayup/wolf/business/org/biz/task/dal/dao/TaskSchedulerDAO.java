package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskSchedulerDO;

public interface TaskSchedulerDAO {
    int deleteById(Long id);

    int insert(TaskSchedulerDO record);

    int insertSelective(TaskSchedulerDO record);

    TaskSchedulerDO selectById(Long id);

    int updateByIdSelective(TaskSchedulerDO record);

    int updateById(TaskSchedulerDO record);
}