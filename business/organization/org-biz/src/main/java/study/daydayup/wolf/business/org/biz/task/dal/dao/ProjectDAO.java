package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.ProjectDO;

public interface ProjectDAO {
    int deleteById(Long id);

    int insert(ProjectDO record);

    int insertSelective(ProjectDO record);

    ProjectDO selectById(Long id);

    int updateByIdSelective(ProjectDO record);

    int updateById(ProjectDO record);
}