package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskStateLogDO;

public interface TaskStateLogDAO {
    int deleteById(Long id);

    int insert(TaskStateLogDO record);

    int insertSelective(TaskStateLogDO record);

    TaskStateLogDO selectById(Long id);

    int updateByIdSelective(TaskStateLogDO record);

    int updateById(TaskStateLogDO record);
}