package study.daydayup.wolf.business.org.biz.task.domain.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.business.org.api.task.dto.TaskOwner;
import study.daydayup.wolf.business.org.api.task.dto.request.task.ProjectRequest;
import study.daydayup.wolf.business.org.api.task.dto.request.task.StaffRequest;
import study.daydayup.wolf.business.org.api.task.dto.request.task.TaskTypeRequest;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDO;
import study.daydayup.wolf.business.org.biz.task.domain.entity.TaskEntity;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.domain.repository
 *
 * @author Wingle
 * @since 2020/3/16 3:50 下午
 **/
@Component
public class TaskQueryRepository extends TaskRepository {
    public List<Task> find(TaskIds taskIds) {
        return null;
    }

    public Page<Task> findAll(@NonNull Long orgId, PageRequest pageRequest) {
        return null;
    }

    public Page<Task> findByParent(TaskId taskId, PageRequest pageRequest) {
        return null;
    }

    public Page<Task> findByTaskType(TaskTypeRequest typeRequest, PageRequest pageRequest) {
        return null;
    }

    public Page<Task> findByProject(ProjectRequest typeRequest, PageRequest pageRequest) {
        return null;
    }

    public Page<Task> findByStaff(StaffRequest staffRequest, PageRequest pageRequest) {
        return null;
    }




    protected List<TaskEntity> findDetailsByTaskList(List<TaskDO> taskDOList, @NonNull TaskOwner owner) {
        return null;
    }
}
