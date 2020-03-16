package study.daydayup.wolf.business.org.biz.task.domain.repository;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.dto.TaskOption;
import study.daydayup.wolf.business.org.biz.task.domain.entity.TaskEntity;
import study.daydayup.wolf.framework.layer.domain.Repository;

/**
 * study.daydayup.wolf.business.org.biz.task.domain.repository
 *
 * @author Wingle
 * @since 2020/3/15 11:54 下午
 **/
@Component
public class TaskRepository implements Repository {

    public TaskEntity find(Long taskId, Long orgId) {
        return find(taskId, orgId, TaskOption.DEFAULT);
    }

    public TaskEntity find(Long taskId, Long orgId, TaskOption option) {
        return null;
    }

    public int add(TaskEntity entity) {
        return 0;
    }

    public int save(TaskEntity entity) {
        return 0;
    }

}
