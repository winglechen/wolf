package study.daydayup.wolf.business.org.api.info.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Company implements Model {
    private Long orgId;

    private Integer orgType;

    private String companyName;

    private String companyLogo;

    private Integer mainBusiness;

    private Integer country;

    private Integer province;

    private Integer city;

    private Integer district;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String tags;

    private String intro;

    private Integer version;

    private Integer deleteFlag;

    private Long lastEditor;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;

}