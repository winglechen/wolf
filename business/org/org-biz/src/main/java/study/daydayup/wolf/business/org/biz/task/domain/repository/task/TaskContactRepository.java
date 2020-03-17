package study.daydayup.wolf.business.org.biz.task.domain.repository.task;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.api.task.domain.entity.task.TaskContact;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskIds;
import study.daydayup.wolf.business.org.biz.task.converter.task.TaskContactConverter;
import study.daydayup.wolf.business.org.biz.task.dal.dao.TaskContactDAO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskContactDO;
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
public class TaskContactRepository implements Repository {
    @Resource
    private TaskContactDAO dao;

    public int add(TaskContact contact) {
        if (contact == null) {
            return 0;
        }

        TaskContactDO contactDO = TaskContactConverter.toDo(contact);
        if (contactDO == null) {
            return 0;
        }
        return dao.insertSelective(contactDO);
    }

    public int save(@NonNull TaskContact key, @NonNull TaskContact changes) {
        if (null == key || null == changes) {
            return 0;
        }
        TaskContactDO keyDO = TaskContactConverter.toDo(key);
        TaskContactDO changesDO = TaskContactConverter.toDo(key);
        if (null == keyDO || null == changesDO) {
            return 0;
        }

        return dao.updateByKey(changesDO, keyDO);
    }

    public TaskContact find(@NonNull TaskId taskId) {
        taskId.valid();

        TaskContactDO tradeDO = dao.selectByTaskId(taskId.getTaskId(), taskId.getOrgId());
        return TaskContactConverter.toModel(tradeDO);
    }

    public List<TaskContact> find(@NonNull TaskIds taskIds) {
        taskIds.valid();

        List<TaskContactDO> tradeDOList = dao.selectByTaskIdIn(taskIds.getTaskIdSet(), taskIds.getOrgId());
        return TaskContactConverter.toModel(tradeDOList);
    }

}
