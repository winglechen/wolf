package study.daydayup.wolf.business.org.biz.task.domain.repository.task;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskTrade;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.business.org.biz.task.converter.task.TaskTradeConverter;
import study.daydayup.wolf.business.org.biz.task.dal.dao.TaskTradeDAO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskTradeDO;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.domain.repository.task
 *
 * @author Wingle
 * @since 2020/3/16 11:03 上午
 **/
@Component
public class TaskTradeRepository implements Repository {
    @Resource
    private TaskTradeDAO dao;

    public int add(TaskTrade taskTrade, @NonNull Long taskId) {
        if (taskTrade == null) {
            return 0;
        }

        TaskTradeDO tradeDO = TaskTradeConverter.toDo(taskTrade);
        if (tradeDO == null) {
            return 0;
        }

        tradeDO.setTaskId(taskId);
        return dao.insertSelective(tradeDO);
    }

    public int save(TaskTrade key, TaskTrade changes) {
        if (null == key || null == changes) {
            return 0;
        }

        TaskTradeDO keyDO = TaskTradeConverter.toDo(key);
        TaskTradeDO changesDO = TaskTradeConverter.toDo(changes);
        if (null == keyDO || null == changesDO) {
            return 0;
        }

        return dao.updateByKey(changesDO, keyDO);
    }

    public TaskTrade find(@NonNull TaskId taskId) {
        taskId.valid();

        TaskTradeDO tradeDO = dao.selectByTaskId(taskId.getTaskId(), taskId.getOrgId());
        return TaskTradeConverter.toModel(tradeDO);
    }

    public List<TaskTrade> find(@NonNull TaskIds taskIds) {
        taskIds.valid();

        List<TaskTradeDO> tradeDOList = dao.selectByTaskIdIn(taskIds.getTaskIdSet(), taskIds.getOrgId());
        return TaskTradeConverter.toModel(tradeDOList);
    }

}
