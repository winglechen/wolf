package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDO;

public interface TaskDAO {
    int deleteById(Long id);

    int insert(TaskDO record);

    int insertSelective(TaskDO record);

    TaskDO selectById(Long id);

    int updateByIdSelective(TaskDO record);

    int updateById(TaskDO record);
}