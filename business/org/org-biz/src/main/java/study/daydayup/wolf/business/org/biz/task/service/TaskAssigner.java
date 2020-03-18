package study.daydayup.wolf.business.org.biz.task.service;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.org.biz.task.dal.dao.TaskAssignmentLogDAO;
import study.daydayup.wolf.business.org.biz.task.dal.dao.TaskDAO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskAssignmentLogDO;
import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.domain.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.task.service
 *
 * @author Wingle
 * @since 2020/3/19 12:16 上午
 **/
@Component
public class TaskAssigner implements Service {
    @Resource
    private TaskDAO taskDAO;
    @Resource
    private TaskAssignmentLogDAO assignmentLogDAO;

    private List<TaskAssignmentLogDO> bulkLog;
    private LocalDateTime now;

    public int bulkAssign(@NonNull List<Long> taskIds, @NonNull Long orgId, @NonNull Long staffId) {
        formatBulkAssignLog(taskIds, orgId, staffId);

        int status = taskDAO.updateStaffIdByIdIn(staffId, taskIds, orgId);
        if (status > 0) {
            saveBulkAssignLog();
        }

        return status;
    }

    private void initNow() {
        if (now != null) {
            return;
        }
        now = LocalDateTime.now();
    }

    private void formatBulkAssignLog(@NonNull List<Long> taskIds, @NonNull Long orgId, @NonNull Long staffId) {
        List<TaskDO> taskDOList = taskDAO.selectByIdIn(taskIds, orgId);
        if (CollectionUtil.isEmpty(taskDOList)) {
            return;
        }

        initNow();
        bulkLog = new ArrayList<>(taskDOList.size());
        for (TaskDO taskDO : taskDOList) {
           taskToAssignLog(taskDO, staffId);
        }
    }

    private void taskToAssignLog(@NonNull TaskDO taskDO, @NonNull Long staffId) {
        TaskAssignmentLogDO log = TaskAssignmentLogDO.builder()
                .orgId(taskDO.getOrgId())
                .taskId(taskDO.getId())
                .projectId(taskDO.getProjectId())

                .assigner(0L)
                .sourceOwner(taskDO.getStaffId())
                .targetOwner(staffId)
                .sourceVersion(taskDO.getVersion())
                .targetVersion(taskDO.getVersion() + 1)

                .memo(StringUtil.BLANK)
                .tags(StringUtil.BLANK)
                .editor(0L)
                .createdAt(now)

                .build();

        bulkLog.add(log);
    }

    private void saveBulkAssignLog() {
        if (CollectionUtil.isEmpty(bulkLog)) {
            return;
        }

        assignmentLogDAO.bulkAddLog(bulkLog);
    }

}
