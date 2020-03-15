package study.daydayup.wolf.business.org.api.task.service;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.domain.event.TaskEvent;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;


/**
 * study.daydayup.wolf.business.org.api.task.service
 *
 * @author Wingle
 * @since 2020/3/15 11:24 下午
 **/
public interface TaskService extends Service {
    Result<Task> find(Long taskId, Long orgId);
    Result<Task> find(Long taskId, Long orgId, Integer taskType);

    Result<Integer> create(Task task);
    Result<Integer> assign(Long taskId, Long orgId, Long staffId);
    Result<Integer> modify(Long taskId, Long orgId, TaskEvent event);

    Result<Page<Task>> findSubTasks(Long taskId, Long orgId, PageRequest pageRequest);
    Result<Page<Task>> findByTaskType(Long orgId, Integer taskType, PageRequest pageRequest);
    Result<Page<Task>> findByProject(Long projectId, Long orgId, PageRequest pageRequest);
}
