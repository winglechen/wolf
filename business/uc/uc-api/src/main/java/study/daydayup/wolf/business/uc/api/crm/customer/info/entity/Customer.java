package study.daydayup.wolf.business.uc.api.crm.customer.info.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Customer implements Model {
    private Long id;

    private Long accountId;

    private Long orgId;

    private Long departmentId;

    private String realName;

    private String nickName;

    private String avatar;

    private LocalDate birthday;

    private Boolean gender;

    private String mobile;

    private String tel;

    private String email;

    private String citizenId;

    private Integer country;

    private Integer province;

    private Integer city;

    private Integer district;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String tags;

    private String intro;

    private String source;

    private String deviceId;

    private Integer version;

    private Boolean deleteFlag;

    private Long lastEditor;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;

}