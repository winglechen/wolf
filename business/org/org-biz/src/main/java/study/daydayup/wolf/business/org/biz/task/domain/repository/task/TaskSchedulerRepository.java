package study.daydayup.wolf.business.org.biz.task.domain.repository.task;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskScheduler;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskSchedulerDO;
import study.daydayup.wolf.framework.layer.domain.Repository;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.domain.repository.task
 *
 * @author Wingle
 * @since 2020/3/16 11:03 上午
 **/
@Component
public class TaskSchedulerRepository implements Repository {
    public int add(TaskScheduler scheduler, @NonNull Long taskId) {
        if (scheduler == null) {
            return 0;
        }

        TaskSchedulerDO schedulerDO = new TaskSchedulerDO();
        schedulerDO.setTaskId(taskId);
        return 0;
    }

    public int save(@NonNull TaskScheduler key, @NonNull TaskScheduler changes) {
        return 0;
    }

    public TaskScheduler find(@NonNull TaskId taskId) {
        return null;
    }

    public List<TaskScheduler> find(@NonNull TaskIds taskIds) {
        return null;
    }

}
