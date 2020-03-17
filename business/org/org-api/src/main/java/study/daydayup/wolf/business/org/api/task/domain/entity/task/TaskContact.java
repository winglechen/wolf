package study.daydayup.wolf.business.org.api.task.domain.entity.task;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.org.api.task.domain.entity.task
 *
 * @author Wingle
 * @since 2020/3/15 10:56 下午
 **/
@Data
public class TaskContact implements Model {
    private Long orgId;
    private Long taskId;

    private Long partnerOrgId;
    private Long customerId;
    private Integer contactPerson;
    private Integer contactMethod;

    private Integer contactState;
    private Integer contactResult;

    private String contactAttachment;
    private String resultAttachment;
    private LocalDateTime recontactAt;
}
