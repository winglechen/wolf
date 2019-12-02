package study.daydayup.wolf.mq.broker.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.mq.broker.dal.dao.auto.QueueAutoDAO;
import study.daydayup.wolf.mq.broker.dal.dataobject.QueueDO;

import java.util.Date;

/**
 * study.daydayup.wolf.mq.broker.dal.dao
 *
 * @author Wingle
 * @since 2019/11/29 3:06 下午
 **/
@Mapper
public interface QueueDAO extends QueueAutoDAO {
    public QueueDO lockByConsumer(@Param("topic") String topic, @Param("consumer") String consumer);

    public int updateConsumerLock(@Param("id") int id, @Param("offset") int offset, @Param("locked_at") Date lockedAt);

    public int unlockByConsumer(@Param("id") int id, @Param("offset") int offset);
}
