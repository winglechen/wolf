package study.daydayup.wolf.business.org.biz.task.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.converter
 *
 * @author Wingle
 * @since 2020/3/15 11:55 下午
 **/
public class TaskConverter implements Converter {
    public static TaskDO toDo(Task task) {
        if (task == null) {
            return null;
        }

        TaskDO taskDO = new TaskDO();
        BeanUtils.copyProperties(task, taskDO);

        return taskDO;
    }

    public static Task toModel(TaskDO taskDO) {
        if (taskDO == null) {
            return null;
        }

        Task task = new Task();
        BeanUtils.copyProperties(taskDO, task);

        return task;
    }

    public static List<Task> toModel(List<TaskDO> taskDOList) {
        return CollectionUtil.to(taskDOList, TaskConverter::toModel);
    }
}
