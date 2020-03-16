package study.daydayup.wolf.business.org.biz.task.domain.repository.task;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskTrade;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.framework.layer.domain.Repository;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.domain.repository.task
 *
 * @author Wingle
 * @since 2020/3/16 11:03 上午
 **/
@Component
public class TaskTradeRepository implements Repository {
    public int add(@NonNull TaskTrade contact) {
        return 0;
    }

    public int save(@NonNull TaskTrade key, @NonNull TaskTrade changes) {
        return 0;
    }

    public TaskTrade find(@NonNull TaskId taskId) {
        return null;
    }

    public List<TaskTrade> find(@NonNull TaskIds taskIds) {
        return null;
    }

}
