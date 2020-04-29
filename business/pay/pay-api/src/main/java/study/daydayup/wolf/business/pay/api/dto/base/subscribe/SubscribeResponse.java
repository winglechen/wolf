package study.daydayup.wolf.business.pay.api.dto.base.subscribe;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Response;

/**
 * study.daydayup.wolf.business.pay.api.dto.base.subscribe
 *
 * @author Wingle
 * @since 2020/4/29 11:08 上午
 **/
@Data
public class SubscribeResponse implements Response {
    private Integer code;
    private String msg;
}
