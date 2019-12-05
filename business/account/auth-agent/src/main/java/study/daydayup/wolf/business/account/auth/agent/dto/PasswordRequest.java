package study.daydayup.wolf.business.account.auth.agent.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.account.auth.agent.dto
 *
 * @author Wingle
 * @since 2019/12/5 10:07 上午
 **/
@Data
public class PasswordRequest {
    @NotBlank
    private String account;
    @NotBlank
    private String password;
    private String RepeatedPassword;
}
