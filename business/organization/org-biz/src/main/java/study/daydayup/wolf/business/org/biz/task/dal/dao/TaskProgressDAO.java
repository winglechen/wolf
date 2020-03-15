package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskProgressDO;

public interface TaskProgressDAO {
    int deleteById(Long id);

    int insert(TaskProgressDO record);

    int insertSelective(TaskProgressDO record);

    TaskProgressDO selectById(Long id);

    int updateByIdSelective(TaskProgressDO record);

    int updateById(TaskProgressDO record);
}