package study.daydayup.wolf.business.org.biz.task.converter.task;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;
import java.util.Map;

/**
 * study.daydayup.wolf.business.org.biz.task.converter.task
 *
 * @author Wingle
 * @since 2020/3/15 11:57 下午
 **/
public class TaskDetailConverter implements Converter {
    public static TaskDetailDO toDo(Task task) {
        if (task == null) {
            return null;
        }

        TaskDetailDO taskDO = new TaskDetailDO();
        BeanUtils.copyProperties(task, taskDO);

        return taskDO;
    }

    public static Task toModel(TaskDetailDO taskDO, @NonNull Task task) {
        if (taskDO == null) {
            return null;
        }

        task.setMemo(taskDO.getMemo());
        task.setExtendFields(taskDO.getExtendFields());

        return task;
    }

    public static List<Task> toModel(List<TaskDetailDO> taskDOList, @NonNull List<Task> taskList) {
        if (CollectionUtil.isEmpty(taskDOList)) {
            return taskList;
        }

        if (taskList.isEmpty()) {
            return taskList;
        }

        Map<Long, TaskDetailDO> doMap = CollectionUtil.map(taskDOList, TaskDetailDO::getTaskId);
        for (Task task : taskList) {
            if (null == doMap.get(task.getId())) {
                continue;
            }

            toModel(doMap.get(task.getId()), task);
        }

        return taskList;
    }
}
