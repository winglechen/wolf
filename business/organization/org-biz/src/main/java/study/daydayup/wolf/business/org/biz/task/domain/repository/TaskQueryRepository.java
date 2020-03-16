package study.daydayup.wolf.business.org.biz.task.domain.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskContact;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskTrade;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.business.org.api.task.dto.TaskOwner;
import study.daydayup.wolf.business.org.api.task.dto.request.task.ProjectRequest;
import study.daydayup.wolf.business.org.api.task.dto.request.task.StaffRequest;
import study.daydayup.wolf.business.org.api.task.dto.request.task.TaskTypeRequest;
import study.daydayup.wolf.business.org.biz.task.converter.TaskConverter;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;
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
    public List<Task> find(@NonNull TaskIds taskIds) {
        taskIds.valid();
        List<TaskDO> taskDOList = taskDAO.selectByIdIn(taskIds.getTaskIdSet(), taskIds.getOrgId());

        return findDetailsByTaskList(taskDOList, taskIds);
    }

    public Page<Task> findAll(@NonNull Long orgId, @NonNull PageRequest pageRequest) {
        Page.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<TaskDO> taskDOList ;

        return null;
    }

    public Page<Task> findByParent(@NonNull TaskId taskId, @NonNull PageRequest pageRequest) {
        return null;
    }

    public Page<Task> findByTaskType(@NonNull TaskTypeRequest typeRequest, @NonNull PageRequest pageRequest) {
        return null;
    }

    public Page<Task> findByProject(@NonNull ProjectRequest typeRequest, @NonNull PageRequest pageRequest) {
        return null;
    }

    public Page<Task> findByStaff(@NonNull StaffRequest staffRequest, @NonNull PageRequest pageRequest) {
        return null;
    }

    protected TaskIds initTaskIds(List<Task> taskList, TaskOwner owner) {
        if (CollectionUtil.isEmpty(taskList)) {
            throw new IllegalArgumentException("taskList can't be empty");
        }

        List<Long> idList = CollectionUtil.keys(taskList, Task::getId);
        if (CollectionUtil.isEmpty(idList)) {
            throw new IllegalArgumentException("taskList can't be empty");
        }

        TaskIds taskIds = TaskIds.builder()
                .orgId(owner.getOrgId())
                .staffId(owner.getStaffId())
                .option(owner.getOption())
                .build();

        taskIds.addAll(idList);
        return taskIds;
    }

    protected List<Task> findDetailsByTaskList(List<TaskDO> taskDOList, @NonNull TaskOwner owner) {
        if (CollectionUtil.isEmpty(taskDOList)) {
            return ListUtil.empty();
        }

        List<Task> taskList = TaskConverter.toModel(taskDOList);
        if (CollectionUtil.isEmpty(taskList)) {
            return taskList;
        }

        TaskIds taskIds = initTaskIds(taskList, owner);
        findDetailByTaskList(taskList, taskIds);
        findContactByTaskList(taskList, taskIds);
        findTradeByTaskList(taskList, taskIds);

        return taskList;
    }

    protected void findDetailByTaskList(@NonNull List<Task> taskList, @NonNull TaskIds taskIds) {
        detailRepository.find(taskIds, taskList);
    }

    protected void findContactByTaskList(@NonNull List<Task> taskList, @NonNull TaskIds taskIds) {
        List<TaskContact> contactList = contactRepository.find(taskIds);
        if (CollectionUtil.isEmpty(contactList)) {
            return;
        }

        CollectionUtil.join(taskList, Task::getId, Task::setContact, contactList, TaskContact::getTaskId);
    }

    protected void findTradeByTaskList(@NonNull List<Task> taskList, @NonNull TaskIds taskIds) {
        List<TaskTrade> tradeList = tradeRepository.find(taskIds);
        if (CollectionUtil.isEmpty(tradeList)) {
            return;
        }
        CollectionUtil.join(taskList, Task::getId, Task::setTrade, tradeList, TaskTrade::getTaskId);
    }

}
