package study.daydayup.wolf.business.org.api.task.dto.request.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.org.api.task.dto.TaskOwner;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.org.api.task.dto.request.task
 *
 * @author Wingle
 * @since 2020/3/16 12:27 上午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProjectRequest extends TaskOwner implements Request {
    private Long projectId;
    private Integer state;
    private Integer taskType;
}
