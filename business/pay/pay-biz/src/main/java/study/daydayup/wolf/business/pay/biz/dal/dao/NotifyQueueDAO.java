package study.daydayup.wolf.business.pay.biz.dal.dao;

import study.daydayup.wolf.business.pay.biz.dal.dataobject.NotifyQueueDO;

public interface NotifyQueueDAO {
    int deleteById(Long id);

    int insert(NotifyQueueDO record);

    int insertSelective(NotifyQueueDO record);

    NotifyQueueDO selectById(Long id);

    int updateByIdSelective(NotifyQueueDO record);

    int updateById(NotifyQueueDO record);
}