package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskTradeDO;

public interface TaskTradeDAO {
    int deleteById(Long id);

    int insert(TaskTradeDO record);

    int insertSelective(TaskTradeDO record);

    TaskTradeDO selectById(Long id);

    int updateByIdSelective(TaskTradeDO record);

    int updateById(TaskTradeDO record);
}