package study.daydayup.wolf.business.uc.api.crm.customer.info.entity;

import lombok.Data;

import study.daydayup.wolf.framework.layer.api.Model;
import java.time.LocalDateTime;

@Data
public class Customer implements Model {

    private Long accountId;
    private Long orgId;

    private Long channelId;

    private String nickname;

    private String avatar;

    private String realName;

    private String mobile;

    private String aadhaarNo;

    private String panNo;

    private String gender;

    private String dob;

    private Long creditAmount;

    private String tags;

    private Integer basicInfoAuthStatus;

    private Integer livenessAuthStatus;

    private Integer aadhaarAuthStatus;

    private Integer panAuthStatus;

    private Integer passportAuthStatus;

    private Integer dlAuthStatus;

    private Integer voterCardAuthStatus;

    private Integer bankCardBindStatus;

    private Integer kycStatus;

    private Integer version;

    private Boolean deleteFlag;

    private Long lastEditor;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;


}