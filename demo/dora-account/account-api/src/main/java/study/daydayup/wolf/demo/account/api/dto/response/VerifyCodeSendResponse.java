package study.daydayup.wolf.demo.account.api.dto.response;

import lombok.Data;

@Data
public class VerifyCodeSendResponse {
    private String mobile;
    private Integer nextSendSeconds;
}
