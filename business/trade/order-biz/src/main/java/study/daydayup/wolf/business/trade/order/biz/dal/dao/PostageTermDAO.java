package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.PostageTermDO;

@Mapper
public interface PostageTermDAO {
    int insert(PostageTermDO record);

    int insertSelective(PostageTermDO record);

    PostageTermDO selectById(Long id);

    int updateByIdSelective(PostageTermDO record);

    int updateById(PostageTermDO record);
}