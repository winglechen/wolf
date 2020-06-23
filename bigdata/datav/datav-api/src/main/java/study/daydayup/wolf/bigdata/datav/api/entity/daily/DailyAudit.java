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
    private String source;

    private Integer installCount = 0;
    private Integer registerCount = 0;
    private Integer verifyCodeCount = 0;

    private Integer basicInfoCount = 0;
    private Integer aadhaarCount = 0;
    private Integer bankCardCount = 0;

    private Integer auditPreviewCount = 0;
    private Integer auditRequestCount = 0;
    private Integer auditPaidCount = 0;
    private BigDecimal auditPaidAmount;

}
