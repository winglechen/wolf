package study.daydayup.wolf.business.account.auth.agent.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * study.daydayup.wolf.business.account.auth.agent.dto
 *
 * @author Wingle
 * @since 2019/12/5 10:07 上午
 **/
@Data
public class SmsRequest {
    @NotBlank
    private String mobile;
    @NotBlank
    private String code;
    private long orgId;
}
