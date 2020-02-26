package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.ModelCollectorDO;

public interface ModelCollectorDAO {
    int deleteById(Long id);

    int insert(ModelCollectorDO record);

    int insertSelective(ModelCollectorDO record);

    ModelCollectorDO selectById(Long id);

    int updateByIdSelective(ModelCollectorDO record);

    int updateById(ModelCollectorDO record);
}