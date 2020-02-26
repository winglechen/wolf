package study.daydayup.wolf.bigdata.datav.api.entity.model;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;


import java.time.LocalDateTime;

@Data
public class ModelCollector implements Model {
    private Long id;

    private Long orgId;

    private Long accountId;

    private Integer collectionCount;

    private Integer caseCount;

    private Integer caseAmount;

    private Integer successCount;

    private Integer successAmount;

    private Integer failCount;

    private Integer failAmount;

    private Boolean deleteFlag;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

}