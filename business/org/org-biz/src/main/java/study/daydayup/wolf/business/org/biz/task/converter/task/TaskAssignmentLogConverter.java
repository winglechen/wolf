package study.daydayup.wolf.business.org.biz.task.converter.task;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskAssignmentLog;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskAssignmentLogDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.converter.task
 *
 * @author Wingle
 * @since 2020/3/15 11:56 下午
 **/
public class TaskAssignmentLogConverter implements Converter {
    public static TaskAssignmentLogDO toDo(TaskAssignmentLog task) {
        if (task == null) {
            return null;
        }

        TaskAssignmentLogDO taskDO = new TaskAssignmentLogDO();
        BeanUtils.copyProperties(task, taskDO);

        return taskDO;
    }

    public static TaskAssignmentLog toModel(TaskAssignmentLogDO taskDO) {
        if (taskDO == null) {
            return null;
        }

        TaskAssignmentLog task = new TaskAssignmentLog();
        BeanUtils.copyProperties(taskDO, task);

        return task;
    }

    public static List<TaskAssignmentLog> toModel(List<TaskAssignmentLogDO> taskDOList) {
        return CollectionUtil.to(taskDOList, TaskAssignmentLogConverter::toModel);
    }
}
