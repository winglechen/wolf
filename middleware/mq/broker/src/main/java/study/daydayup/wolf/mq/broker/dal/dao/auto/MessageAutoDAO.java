package study.daydayup.wolf.mq.broker.dal.dao.auto;

import study.daydayup.wolf.mq.broker.dal.dataobject.MessageDO;

public interface MessageAutoDAO {
    int insert(MessageDO record);

    int insertSelective(MessageDO record);

    MessageDO selectById(Integer id);

    int updateByIdSelective(MessageDO record);

    int updateById(MessageDO record);
}