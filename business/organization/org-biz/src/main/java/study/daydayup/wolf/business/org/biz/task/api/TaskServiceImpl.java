package study.daydayup.wolf.business.org.biz.task.api;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.domain.event.TaskEvent;
import study.daydayup.wolf.business.org.api.task.service.TaskService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.org.biz.task.api
 *
 * @author Wingle
 * @since 2020/3/16 12:44 上午
 **/
@RpcService(protocol = "dubbo")
public class TaskServiceImpl implements TaskService {
    @Override
    public Result<Task> find(Long taskId, Long orgId) {
        return null;
    }

    @Override
    public Result<Task> find(Long taskId, Long orgId, Integer taskType) {
        return null;
    }

    @Override
    public Result<Integer> create(Task task) {
        return null;
    }

    @Override
    public Result<Integer> assign(Long taskId, Long orgId, Long staffId) {
        return null;
    }

    @Override
    public Result<Integer> modify(Long taskId, Long orgId, TaskEvent event) {
        return null;
    }

    @Override
    public Result<Page<Task>> findSubTasks(Long taskId, Long orgId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Result<Page<Task>> findByTaskType(Long orgId, Integer taskType, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Result<Page<Task>> findByProject(Long projectId, Long orgId, PageRequest pageRequest) {
        return null;
    }
}
