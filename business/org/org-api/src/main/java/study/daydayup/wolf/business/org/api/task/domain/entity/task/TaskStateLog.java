package study.daydayup.wolf.business.org.api.task.domain.entity.task;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.org.api.task.domain.entity.task
 *
 * @author Wingle
 * @since 2020/3/18 11:12 下午
 **/
@Data
@Builder
@NoArgsConstructor
public class TaskStateLog implements Model {
    private Long orgId;
    private Long staffId;
    private Long taskId;
    private Long projectId;

    private Integer sourceState;
    private Integer targetState;

    private Integer sourceVersion;
    private Integer targetVersion;

    private String memo;
    private String tags;

    private Integer deleteFlag;
    private Long editor;
    private LocalDateTime createdAt;
}
