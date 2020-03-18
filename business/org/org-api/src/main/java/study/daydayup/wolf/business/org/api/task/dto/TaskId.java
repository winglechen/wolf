package study.daydayup.wolf.business.org.api.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.framework.layer.api.DTO;

/**
 * study.daydayup.wolf.business.org.api.task.dto
 *
 * @author Wingle
 * @since 2020/3/16 10:39 上午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TaskId extends TaskOwner implements DTO {
    private Long taskId;
}
