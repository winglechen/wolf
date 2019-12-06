package study.daydayup.wolf.mq.broker.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.mq.broker.dal.dataobject.MessageDO;

@Mapper
public interface MessageAutoDAO {
    int insert(MessageDO record);

    int insertSelective(MessageDO record);

    MessageDO selectById(Integer id);

    int updateByIdSelective(MessageDO record);

    int updateById(MessageDO record);
}