package study.daydayup.wolf.bigdata.datav.api.entity.daily;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DailySms implements Model {
    private Long id;

    private Long orgId;

    private LocalDate date;

    private Integer smsType;

    private Integer smsCount;

    private Boolean deleteFlag;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

}