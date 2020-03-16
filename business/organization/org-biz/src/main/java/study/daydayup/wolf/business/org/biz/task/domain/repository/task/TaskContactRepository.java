package study.daydayup.wolf.business.org.biz.task.domain.repository.task;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskContact;
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
public class TaskContactRepository implements Repository {

    public int add(@NonNull TaskContact contact) {
        return 0;
    }

    public int save(@NonNull TaskContact key, @NonNull TaskContact changes) {
        return 0;
    }

    public TaskContact find(@NonNull TaskId taskId) {
        return null;
    }

    public List<TaskContact> find(@NonNull TaskIds taskIds) {
        return null;
    }

}
