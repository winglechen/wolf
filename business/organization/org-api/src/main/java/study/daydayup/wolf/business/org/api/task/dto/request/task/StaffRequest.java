package study.daydayup.wolf.business.org.api.task.dto.request.task;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.org.api.task.dto.request.task
 *
 * @author Wingle
 * @since 2020/3/16 12:27 上午
 **/
@Data
public class StaffRequest implements Request {
    @NotNull @Min(1)
    private Long orgId;
    @NotNull @Min(1)
    private Long staffId;

    private Integer taskType;
}
