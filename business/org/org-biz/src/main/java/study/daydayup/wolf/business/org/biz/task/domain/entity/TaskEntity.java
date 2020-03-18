package study.daydayup.wolf.business.org.biz.task.domain.entity;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskContact;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskScheduler;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskTrade;
import study.daydayup.wolf.business.org.api.task.domain.event.TaskEvent;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.util.Collection;
import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.domain.entity
 *
 * @author Wingle
 * @since 2020/3/15 11:42 下午
 **/
public class TaskEntity extends AbstractEntity<Task> implements Entity {
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
    }

    public void assign(Long staffId) {
        initChanges();
        model.setStaffId(staffId);
        changes.setStaffId(staffId);
    }

    //TODO add task state machine
    public void changeState(TaskEvent event) {

    }

    public void changeState(Integer state) {
        initChanges();
        model.setState(state);
        changes.setState(state);
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
}

