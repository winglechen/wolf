package study.daydayup.wolf.business.org.biz.task.dal.dao;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDO;

public interface TaskDAO {
    int deleteById(Long id);

    int insert(TaskDO record);

    int insertSelective(TaskDO record);

    TaskDO selectById(@Param("id")Long id, @Param("orgId")Long orgId);

    int updateByIdSelective(TaskDO record);

    int updateById(TaskDO record);

    List<TaskDO> selectByIdIn(@Param("idCollection")Collection<Long> idCollection, @Param("orgId")Long orgId);

    int updateByKey(@Param("updated") TaskDO updated, @Param("key") TaskDO key );

}