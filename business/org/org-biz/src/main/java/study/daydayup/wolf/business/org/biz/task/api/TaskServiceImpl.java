package study.daydayup.wolf.business.org.biz.task.api;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.domain.event.TaskEvent;
import study.daydayup.wolf.business.org.api.task.domain.exception.TaskNotFoundException;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.business.org.api.task.dto.TaskOption;
import study.daydayup.wolf.business.org.api.task.dto.request.task.ProjectRequest;
import study.daydayup.wolf.business.org.api.task.dto.request.task.StaffRequest;
import study.daydayup.wolf.business.org.api.task.dto.request.task.TaskTypeRequest;
import study.daydayup.wolf.business.org.api.task.service.TaskService;
import study.daydayup.wolf.business.org.biz.task.dal.dao.TaskDAO;
import study.daydayup.wolf.business.org.biz.task.domain.entity.TaskEntity;
import study.daydayup.wolf.business.org.biz.task.domain.repository.TaskQueryRepository;
import study.daydayup.wolf.business.org.biz.task.domain.repository.TaskRepository;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private TaskQueryRepository queryRepository;
    @Resource
    protected TaskDAO taskDAO;

    @Override
    public Result<Task> find(Long taskId, Long orgId) {
        return find(taskId, orgId, TaskOption.DEFAULT);
    }

    @Override
    public Result<Task> find(@NonNull Long taskId, @NonNull Long orgId, @NonNull TaskOption option) {
        TaskEntity entity = taskRepository.find(taskId, orgId, option);
        if (entity == null) {
            throw new TaskNotFoundException();
        }

        return Result.ok(entity.getModel());
    }

    @Override
    public Result<Task> find(@NonNull TaskId taskId) {
        TaskEntity entity = taskRepository.find(taskId);
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
    public Result<Integer> assign(@NonNull Long taskId, @NonNull Long orgId, @NonNull Long staffId) {
        TaskEntity entity = taskRepository.find(taskId, orgId);
        entity.assign(staffId);
        int status = taskRepository.save(entity);
        return Result.ok(status);
    }

    @Override
    public Result<Integer> assign(@NonNull List<Long> taskIds, @NonNull Long orgId, @NonNull Long staffId) {
        if (CollectionUtil.isEmpty(taskIds)) {
            return null;
        }

        int status = taskDAO.updateStaffIdByIdIn(staffId, taskIds, orgId);
        //TODO log assignment

        return Result.ok(status);
    }

    @Override
    public Result<Integer> modify(@NonNull TaskEvent event) {
        TaskEntity entity = taskRepository.find(event.getTaskId(), event.getOrgId());
        entity.changeState(event);
        int status = taskRepository.save(entity);
        return Result.ok(status);
    }

    @Override
    public Result<Page<Task>> findByOrg(Long orgId, PageRequest pageRequest) {
        return findByOrg(orgId, TaskOption.DEFAULT, pageRequest);
    }

    @Override
    public Result<List<Task>> find(TaskIds taskIds) {
        taskIds.valid();
        List<Task> taskList = queryRepository.find(taskIds);
        return Result.ok(taskList);
    }

    @Override
    public Result<Page<Task>> findByOrg(@NonNull Long orgId, TaskOption option, @NonNull PageRequest pageRequest) {
        Page<Task> taskList = queryRepository.findByOrg(orgId, option, pageRequest);
        return Result.ok(taskList);
    }

    @Override
    public Result<Page<Task>> findSubTasks(@NonNull TaskId taskId, @NonNull PageRequest pageRequest) {
        Page<Task> taskList = queryRepository.findByParent(taskId, pageRequest);
        return Result.ok(taskList);
    }

    @Override
    public Result<Page<Task>> findByStaff(@NonNull StaffRequest staffRequest, @NonNull PageRequest pageRequest) {
        Page<Task> taskList = queryRepository.findByStaff(staffRequest, pageRequest);
        return Result.ok(taskList);
    }

    @Override
    public Result<Page<Task>> findByTaskType(@NonNull TaskTypeRequest typeRequest, @NonNull PageRequest pageRequest) {
        Page<Task> taskList = queryRepository.findByTaskType(typeRequest, pageRequest);
        return Result.ok(taskList);
    }

    @Override
    public Result<Page<Task>> findByProject(@NonNull ProjectRequest projectRequest, @NonNull PageRequest pageRequest) {
        Page<Task> taskList = queryRepository.findByProject(projectRequest, pageRequest);
        return Result.ok(taskList);
    }
}
