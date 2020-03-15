package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskContactDO;

public interface TaskContactDAO {
    int deleteById(Long id);

    int insert(TaskContactDO record);

    int insertSelective(TaskContactDO record);

    TaskContactDO selectById(Long id);

    int updateByIdSelective(TaskContactDO record);

    int updateById(TaskContactDO record);
}