package study.daydayup.wolf.business.org.api.task.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.org.api.task.entity
 *
 * @author Wingle
 * @since 2020/3/13 2:19 下午
 **/
@Data
public class Task implements Model {
    private Long id;
    private Long orgId;
    private Long staffId;

    private Long projectId;
    private Long parentId;

    private String name;
    private Integer taskType;
    private Integer state;

    private BigDecimal progressRate;
    private Integer priority;
    private Integer workLoad;
    private Integer storyPoints;

    private String memo;
    private String tags;

}
