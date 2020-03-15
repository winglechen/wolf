package study.daydayup.wolf.business.org.api.task.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

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

    private String name;
    private Integer categoryId;
    private Integer taskType;

    private Integer state;

    private String memo;
    private String tags;

}
