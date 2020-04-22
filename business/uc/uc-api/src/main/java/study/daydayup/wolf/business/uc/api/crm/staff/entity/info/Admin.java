package study.daydayup.wolf.business.uc.api.crm.staff.entity.info;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Admin implements Model {
    private Long orgId;
    private Long accountId;

    private String username;
    private String nickname;
    private String mobile;
    private String avatar;
    private Integer status;

    private Integer deleteFlag;
    private Integer version;
    private Long lastEditor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;

}