package study.daydayup.wolf.business.org.api.task.domain.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

/**
 * study.daydayup.wolf.business.org.api.task.domain.entity
 *
 * @author Wingle
 * @since 2020/3/15 10:33 下午
 **/
@Data
public class Project implements Model {
    private Long id;
    private Long orgId;
    private Long parentId;

    private String name;
    private Integer priority;

    private String detail;
    private String tags;
}
