package study.daydayup.wolf.business.org.biz.task.converter.task;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskContact;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskContactDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.converter.task
 *
 * @author Wingle
 * @since 2020/3/15 11:57 下午
 **/
public class TaskContactConverter implements Converter {
    public static TaskContactDO toDo(TaskContact task) {
        if (task == null) {
            return null;
        }

        TaskContactDO taskDO = new TaskContactDO();
        BeanUtils.copyProperties(task, taskDO);

        return taskDO;
    }

    public static TaskContact toModel(TaskContactDO taskDO) {
        if (taskDO == null) {
            return null;
        }

        TaskContact task = new TaskContact();
        BeanUtils.copyProperties(taskDO, task);

        return task;
    }

    public static List<TaskContact> toModel(List<TaskContactDO> taskDOList) {
        return CollectionUtil.to(taskDOList, TaskContactConverter::toModel);
    }
}
