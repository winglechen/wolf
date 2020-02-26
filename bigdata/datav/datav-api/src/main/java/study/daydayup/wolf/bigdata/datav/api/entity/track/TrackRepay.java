package study.daydayup.wolf.bigdata.datav.api.entity.track;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TrackRepay implements Model {
    private Long id;

    private Long orgId;

    private LocalDate requestDate;

    private LocalDate loanDate;

    private LocalDate dueDate;

    private Long goodsId;

    private Integer installmentNo;

    private Integer prepayCount;

    private BigDecimal prepayAmount;

    private Integer repayCount;

    private BigDecimal repayAmount;

    private Integer overdueCount;

    private BigDecimal overdueAmount;

    private Integer lossCount;

    private BigDecimal lossAmount;

    private Integer partlyLossCount;

    private BigDecimal partlyLossAmount;

    private Integer d1;

    private Integer d2;

    private Integer d3;

    private Integer d4;

    private Integer d5;

    private Integer d6;

    private Integer d7;

    private Integer w2;

    private Integer w3;

    private Integer w4;

    private Integer m1;

    private Integer mn;

    private Boolean deleteFlag;

    private LocalDateTime createdAt;

}