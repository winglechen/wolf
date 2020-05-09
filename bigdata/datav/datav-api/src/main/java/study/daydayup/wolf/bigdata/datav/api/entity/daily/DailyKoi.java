package study.daydayup.wolf.bigdata.datav.api.entity.daily;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DailyKoi implements Model {
    private Long orgId;
    private LocalDate date;
    private String source;

    private Integer pv;
    private Integer uv;

    private Integer installCount;
    private Integer registerCount;

    private Integer basicInfoCount;
    private Integer aadhaarCount;
    private Integer bankCardCount;
    private Integer authSuccessCount;

    private Integer livenessCount;
    private Integer panCardCount;
    private Integer passportCount;
    private Integer drivingLicenseCount;
    private Integer voterCount;
    private Integer kycCount;
    private Integer creditPromoteCount;


    private Boolean deleteFlag;
    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;
}