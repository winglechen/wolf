package study.daydayup.wolf.business.org.biz.task.domain.entity;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
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

    private void initChanges() {
        if (changes != null) {
            return;
        }

        changes = new Task();
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
}

