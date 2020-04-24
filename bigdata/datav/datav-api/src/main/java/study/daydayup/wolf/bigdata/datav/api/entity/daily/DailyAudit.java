package study.daydayup.wolf.bigdata.datav.api.entity.daily;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * study.daydayup.wolf.bigdata.datav.api.entity.daily
 *
 * @author Wingle
 * @since 2020/4/24 2:55 下午
 **/
@Data
public class DailyAudit implements Model {
    private Long id;
    private Long orgId;
    private LocalDate date;

    private Integer requestCount;
    private Integer payCount;
    private BigDecimal payAmount;

}
