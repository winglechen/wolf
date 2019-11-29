package study.daydayup.wolf.mq.broker.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.mq.broker.dal.dataobject.QueueDO;

@Mapper
public interface QueueAutoDAO {
    int insert(QueueDO record);

    int insertSelective(QueueDO record);

    QueueDO selectById(Integer id);

    int updateByIdSelective(QueueDO record);

    int updateById(QueueDO record);
}