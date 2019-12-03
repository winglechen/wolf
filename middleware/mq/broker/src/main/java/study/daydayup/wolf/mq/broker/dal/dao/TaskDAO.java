package study.daydayup.wolf.mq.broker.dal.dao;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.mq.broker.dal.dao.auto.TaskAutoDAO;

import java.util.Date;

/**
 * study.daydayup.wolf.mq.broker.dal.dao
 *
 * @author Wingle
 * @since 2019/11/29 3:08 下午
 **/
public interface TaskDAO extends TaskAutoDAO {
    int updateStateById(@Param("id") long id, @Param("state") byte state, @Param("updated_at") Date updateAt);
}
