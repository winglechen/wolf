package study.daydayup.wolf.bigdata.datav.biz.dal.dao;

import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.OffsetHolderDO;

public interface OffsetHolderDAO {
    int deleteById(Long id);

    int insert(OffsetHolderDO record);

    int insertSelective(OffsetHolderDO record);

    OffsetHolderDO selectById(Long id);

    int updateByIdSelective(OffsetHolderDO record);

    int updateById(OffsetHolderDO record);
}