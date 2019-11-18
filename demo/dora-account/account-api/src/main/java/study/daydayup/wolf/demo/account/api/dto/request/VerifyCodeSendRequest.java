package study.daydayup.wolf.demo.account.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class VerifyCodeSendRequest {

    @NotBlank
    @Size(min = 11, max = 11, message = "请输入正确手机号码")
    private String mobile;
}
