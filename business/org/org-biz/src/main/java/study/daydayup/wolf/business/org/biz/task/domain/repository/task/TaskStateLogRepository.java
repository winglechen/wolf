package study.daydayup.wolf.business.org.biz.task.domain.repository.task;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskStateLog;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.business.org.biz.task.converter.task.TaskStateLogConverter;
import study.daydayup.wolf.business.org.biz.task.dal.dao.TaskStateLogDAO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskStateLogDO;
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
public class TaskStateLogRepository implements Repository {
    @Resource
    private TaskStateLogDAO dao;

    public int add(TaskStateLog stateLog) {
        if (stateLog == null) {
            return 0;
        }

        TaskStateLogDO logDO = TaskStateLogConverter.toDo(stateLog);
        if (logDO == null) {
            return 0;
        }

        return dao.insertSelective(logDO);
    }


    public TaskStateLog find(@NonNull TaskId taskId) {
        taskId.valid();

        TaskStateLogDO logDO = dao.selectByTaskId(taskId.getTaskId(), taskId.getOrgId());
        return TaskStateLogConverter.toModel(logDO);
    }

    public List<TaskStateLog> find(@NonNull TaskIds taskIds) {
        taskIds.valid();

        List<TaskStateLogDO> logDOList = dao.selectByTaskIdIn(taskIds.getTaskIdSet(), taskIds.getOrgId());
        return TaskStateLogConverter.toModel(logDOList);
    }

}
