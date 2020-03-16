package study.daydayup.wolf.business.org.biz.task.converter.task;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskTrade;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskTradeDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.converter.task
 *
 * @author Wingle
 * @since 2020/3/15 11:56 下午
 **/
public class TaskTradeConverter implements Converter {
    public static TaskTradeDO toDo(TaskTrade task) {
        if (task == null) {
            return null;
        }

        TaskTradeDO taskDO = new TaskTradeDO();
        BeanUtils.copyProperties(task, taskDO);

        return taskDO;
    }

    public static TaskTrade toModel(TaskTradeDO taskDO) {
        if (taskDO == null) {
            return null;
        }

        TaskTrade task = new TaskTrade();
        BeanUtils.copyProperties(taskDO, task);

        return task;
    }

    public static List<TaskTrade> toModel(List<TaskTradeDO> taskDOList) {
        return CollectionUtil.to(taskDOList, TaskTradeConverter::toModel);
    }
}
