package study.daydayup.wolf.business.org.biz.task.domain.repository.task;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.business.org.biz.task.converter.task.TaskDetailConverter;
import study.daydayup.wolf.business.org.biz.task.dal.dao.TaskDetailDAO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDetailDO;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.domain.repository.task
 *
 * @author Wingle
 * @since 2020/3/16 11:03 上午
 **/
@Component
public class TaskDetailRepository implements Repository {
    @Resource
    private TaskDetailDAO dao;

    public int add(@NonNull Task detail, @NonNull Long taskId) {
        if (null == detail.getMemo() && null == detail.getExtendFields()) {
            return 0;
        }

        TaskDetailDO detailDO = TaskDetailConverter.toDo(detail);
        if (detailDO == null) {
            return 0;
        }
        detailDO.setId(taskId);
        return dao.insertSelective(detailDO);
    }

    public int save(Task key, Task changes) {
        if (null == key || null == changes) {
            return 0;
        }

        if (null == changes.getMemo() && null == changes.getExtendFields()) {
            return 0;
        }

        TaskDetailDO keyDO = TaskDetailConverter.toDo(key);
        TaskDetailDO changesDO = TaskDetailConverter.toDo(changes);
        if (null == keyDO || null == changesDO) {
            return 0;
        }

        return dao.updateByKey(changesDO, keyDO);
    }

    public Task find(@NonNull TaskId taskId, @NonNull Task task) {
        taskId.valid();

        TaskDetailDO tradeDO = dao.selectByTaskId(taskId.getTaskId(), taskId.getOrgId());
        return TaskDetailConverter.toModel(tradeDO, task);
    }

    public List<Task> find(@NonNull TaskIds taskIds, @NonNull List<Task> taskList) {
        taskIds.valid();

        List<TaskDetailDO> tradeDOList = dao.selectByTaskIdIn(taskIds.getTaskIdSet(), taskIds.getOrgId());
        return TaskDetailConverter.toModel(tradeDOList, taskList);
    }

}
