package study.daydayup.wolf.business.account.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenApp implements Model {
    private Long orgId;

    private Integer appType;
    private String appId;
    private String appSecret;
    private String memo;

    private Integer version;
    private Boolean deleteFlag;
    private Long lastEditor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;

}