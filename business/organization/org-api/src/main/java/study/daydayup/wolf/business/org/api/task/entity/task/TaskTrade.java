package study.daydayup.wolf.business.org.api.task.entity.task;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.org.api.task.entity.task
 *
 * @author Wingle
 * @since 2020/3/15 11:01 下午
 **/
@Data
public class TaskTrade implements Model {
    private Long orgId;
    private Long taskId;

    private Integer tradeType;
    private String tradeNo;
    private Integer installmentNo;

    private Integer currency;
    private BigDecimal amount;
}
