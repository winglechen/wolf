package study.daydayup.wolf.business.org.biz.task.converter.task;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskStateLog;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskStateLogDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.converter.task
 *
 * @author Wingle
 * @since 2020/3/15 11:56 下午
 **/
public class TaskStateLogConverter implements Converter {
    public static TaskStateLogDO toDo(TaskStateLog task) {
        if (task == null) {
            return null;
        }

        TaskStateLogDO taskDO = new TaskStateLogDO();
        BeanUtils.copyProperties(task, taskDO);

        return taskDO;
    }

    public static TaskStateLog toModel(TaskStateLogDO taskDO) {
        if (taskDO == null) {
            return null;
        }

        TaskStateLog task = new TaskStateLog();
        BeanUtils.copyProperties(taskDO, task);

        return task;
    }

    public static List<TaskStateLog> toModel(List<TaskStateLogDO> taskDOList) {
        return CollectionUtil.to(taskDOList, TaskStateLogConverter::toModel);
    }
}
