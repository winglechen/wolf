package study.daydayup.wolf.demo.account.api.dto.response;

import lombok.Data;

@Data
public class VerifyCodeResponse {
    private String mobile;
    private int nextSendSeconds;
}
