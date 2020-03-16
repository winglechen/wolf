package study.daydayup.wolf.business.org.biz.task.api;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.domain.event.TaskEvent;
import study.daydayup.wolf.business.org.api.task.domain.exception.TaskNotFoundException;
import study.daydayup.wolf.business.org.api.task.dto.TaskOption;
import study.daydayup.wolf.business.org.api.task.service.TaskService;
import study.daydayup.wolf.business.org.biz.task.domain.entity.TaskEntity;
import study.daydayup.wolf.business.org.biz.task.domain.repository.TaskRepository;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.org.biz.task.api
 *
 * @author Wingle
 * @since 2020/3/16 12:44 上午
 **/
@RpcService(protocol = "dubbo")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskRepository taskRepository;


    @Override
    public Result<Task> find(Long taskId, Long orgId) {
        return find(taskId, orgId, TaskOption.DEFAULT);
    }

    @Override
    public Result<Task> find(Long taskId, Long orgId, TaskOption option) {
        TaskEntity entity = taskRepository.find(taskId, orgId, option);
        if (entity == null) {
            throw new TaskNotFoundException();
        }

        return Result.ok(entity.getModel());
    }

    @Override
    public Result<Integer> add(@Validated Task task) {
        TaskEntity entity = new TaskEntity(task);
        int status = taskRepository.add(entity);

        return Result.ok(status);
    }

    @Override
    public Result<Integer> assign(Long taskId, Long orgId, Long staffId) {
        TaskEntity entity = taskRepository.find(taskId, orgId);
        entity.assign(staffId);
        int status = taskRepository.save(entity);
        return Result.ok(status);
    }

    @Override
    public Result<Integer> modify(Long taskId, Long orgId, TaskEvent event) {
        TaskEntity entity = taskRepository.find(taskId, orgId);
        entity.modify(event);
        int status = taskRepository.save(entity);
        return Result.ok(status);
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
