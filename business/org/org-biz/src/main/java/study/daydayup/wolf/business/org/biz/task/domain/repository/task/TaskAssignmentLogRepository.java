package study.daydayup.wolf.business.org.biz.task.domain.repository.task;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskAssignmentLog;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.business.org.biz.task.converter.task.TaskAssignmentLogConverter;
import study.daydayup.wolf.business.org.biz.task.dal.dao.TaskAssignmentLogDAO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskAssignmentLogDO;
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
public class TaskAssignmentLogRepository implements Repository {
    @Resource
    private TaskAssignmentLogDAO dao;

    public int add(TaskAssignmentLog assignmentLog) {
        if (assignmentLog == null) {
            return 0;
        }

        TaskAssignmentLogDO logDO = TaskAssignmentLogConverter.toDo(assignmentLog);
        if (logDO == null) {
            return 0;
        }

        return dao.insertSelective(logDO);
    }

    public TaskAssignmentLog find(@NonNull TaskId taskId) {
        taskId.valid();

        TaskAssignmentLogDO logDO = dao.selectByTaskId(taskId.getTaskId(), taskId.getOrgId());
        return TaskAssignmentLogConverter.toModel(logDO);
    }

    public List<TaskAssignmentLog> find(@NonNull TaskIds taskIds) {
        taskIds.valid();

        List<TaskAssignmentLogDO> logDOList = dao.selectByTaskIdIn(taskIds.getTaskIdSet(), taskIds.getOrgId());
        return TaskAssignmentLogConverter.toModel(logDOList);
    }

}
