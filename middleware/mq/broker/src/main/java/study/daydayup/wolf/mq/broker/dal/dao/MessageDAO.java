package study.daydayup.wolf.mq.broker.dal.dao;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.mq.broker.dal.dao.auto.MessageAutoDAO;
import study.daydayup.wolf.mq.broker.dal.dataobject.MessageDO;

/**
 * study.daydayup.wolf.mq.broker.dal.dao
 *
 * @author Wingle
 * @since 2019/11/29 3:05 下午
 **/
public interface MessageDAO extends MessageAutoDAO {
    MessageDO selectNextTopicMessage(@Param("topic") String topic, @Param("shard") byte shard, @Param("id") int id);
}
