package study.daydayup.wolf.business.org.biz.task.domain.entity;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.*;
import study.daydayup.wolf.business.org.api.task.domain.event.TaskEvent;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.domain.entity
 *
 * @author Wingle
 * @since 2020/3/15 11:42 下午
 **/
public class TaskEntity extends AbstractEntity<Task> implements Entity {
    private LocalDateTime now;

    public TaskEntity(Task task) {
        this(task, true);
    }

    public TaskEntity(Task task, boolean isNew) {
        this.isNew = isNew;

        model = task;
        key   = Task.builder()
                .id(task.getId())
                .orgId(task.getOrgId())
                .staffId(task.getStaffId())
                .taskType(task.getTaskType())
                .build();
    }

    public void format() {
        formatContact();
        formatTrade();
        formatScheduler();

        addStateLog();
    }

    public void assign(Long staffId) {
        initChanges();
        model.setStaffId(staffId);
        changes.setStaffId(staffId);

        addAssignmentLog();
    }

    //TODO add task state machine
    public void changeState(TaskEvent event) {

    }

    public void changeState(Integer state) {
        initChanges();
        model.setState(state);
        changes.setState(state);
    }

    private void initNow() {
        if (now != null) {
            return;
        }

        now = LocalDateTime.now();
    }

    private void initChanges() {
        if (changes != null) {
            return;
        }

        changes = new Task();
    }

    private void formatContact() {
        TaskContact contact = model.getContact();
        if (null == contact) {
            return;
        }

        contact.setOrgId(model.getOrgId());
        contact.setStaffId(model.getStaffId());
        contact.setTaskId(model.getId());
    }

    private void formatTrade() {
        TaskTrade trade = model.getTrade();
        if (null == trade) {
            return;
        }

        trade.setOrgId(model.getOrgId());
        trade.setStaffId(model.getStaffId());
        trade.setTaskId(model.getId());
    }

    private void formatScheduler() {
        TaskScheduler scheduler = model.getScheduler();
        if (scheduler == null) {
            return;
        }

        scheduler.setOrgId(model.getOrgId());
        scheduler.setStaffId(model.getStaffId());
        scheduler.setTaskId(model.getId());
    }

    private void addStateLog() {
        initNow();
        TaskStateLog log = TaskStateLog.builder()
                .orgId(model.getOrgId())
                .staffId(model.getStaffId())
                .taskId(model.getId())
                .projectId(model.getProjectId())

                .sourceState(key.getState())
                .targetState(changes.getState())
                .sourceVersion(model.getVersion())
                .targetVersion(model.getVersion() + 1)

                .createdAt(now)
                .build();

        changes.setStateLog(log);
    }

    private void addAssignmentLog() {
        initNow();
        TaskAssignmentLog log = TaskAssignmentLog.builder()
                .orgId(model.getOrgId())
                .taskId(model.getId())
                .projectId(model.getProjectId())

                .sourceOwner(key.getStaffId())
                .targetOwner(changes.getStaffId())
                .sourceVersion(model.getVersion())
                .targetVersion(model.getVersion() + 1)

                .createdAt(now)
                .build();

        changes.setAssignmentLog(log);
    }

}

