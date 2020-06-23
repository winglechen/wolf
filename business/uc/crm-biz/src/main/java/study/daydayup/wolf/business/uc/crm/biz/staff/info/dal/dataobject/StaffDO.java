package study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class StaffDO implements Serializable {
    private Long id;

    private Long accountId;

    private Long orgId;

    private Long departmentId;

    private String realName;

    private String nickName;

    private String avatar;

    private LocalDate birthday;

    private Boolean gender;

    private String phone;

    private String tel;

    private String email;

    private String nationalId;

    private String roles;

    private String title;

    private Long pNo;

    private Long mNo;

    private String tags;

    private String intro;

    private Integer version;

    private Boolean deleteFlag;

    private Long lastEditor;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;

}