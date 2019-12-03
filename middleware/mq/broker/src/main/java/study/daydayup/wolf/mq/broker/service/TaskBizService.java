package study.daydayup.wolf.mq.broker.service;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.mq.broker.dal.dao.TaskDAO;
import study.daydayup.wolf.mq.broker.dal.dataobject.TaskDO;
import study.daydayup.wolf.mq.client.entity.Message;
import study.daydayup.wolf.mq.client.entity.Task;
import study.daydayup.wolf.mq.client.enums.TaskStateEnum;

import javax.annotation.Resource;
import java.util.Date;

/**
 * study.daydayup.wolf.mq.broker.service
 *
 * @author Wingle
 * @since 2019/12/3 4:51 下午
 **/
@Component
public class TaskBizService {
    @Resource
    private TaskDAO taskDAO;

    public Task createTask(String consumer, Message message) {
        TaskDO taskDO = new TaskDO();
        BeanUtils.copyProperties(message, taskDO);
        taskDO.setConsumer(consumer);

        Task task = new Task();
        long taskId = taskDAO.insertSelective(taskDO);
        task.setMessage(message);
        task.setTaskId(taskId);

        return task;
    }

    public void finishTask(long taskId) {
        taskDAO.updateStateById(taskId, (byte) TaskStateEnum.DONE.getCode(), new Date());
    }

    public void failTask(long taskId) {
        taskDAO.updateStateById(taskId, (byte) TaskStateEnum.FAIL.getCode(), new Date());
    }
}
