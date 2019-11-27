package study.daydayup.wolf.business.account.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.account.api.dto.request
 *
 * @author Wingle
 * @since 2019/11/21 9:56 下午
 **/
@Data
public class SmsCodeRequest extends Request {
    private String mobile;
}