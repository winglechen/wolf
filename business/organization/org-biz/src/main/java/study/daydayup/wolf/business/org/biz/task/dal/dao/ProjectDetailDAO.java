package study.daydayup.wolf.business.org.biz.task.dal.dao;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.ProjectDetailDO;

public interface ProjectDetailDAO {
    int deleteById(Long id);

    int insert(ProjectDetailDO record);

    int insertSelective(ProjectDetailDO record);

    ProjectDetailDO selectById(Long id);

    int updateByIdSelective(ProjectDetailDO record);

    int updateByPrimaryKeyWithBLOBs(ProjectDetailDO record);

    int updateById(ProjectDetailDO record);
}