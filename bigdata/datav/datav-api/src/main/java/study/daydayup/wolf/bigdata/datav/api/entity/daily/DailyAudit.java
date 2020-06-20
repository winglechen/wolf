package study.daydayup.wolf.bigdata.datav.api.entity.daily;

import lombok.Data;
import study.daydayup.wolf.common.model.type.number.base.Int;
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
    private String source;

    private Integer installCount;
    private Integer registerCount;
    private Integer verifyCodeCount;

    private Integer basicInfoCount;
    private Integer aadhaarCount;
    private Integer bankCardCount;

    private Integer auditPreviewCount;
    private Integer auditRequestCount;
    private Integer auditPaidCount;
    private BigDecimal auditPaidAmount;

}
