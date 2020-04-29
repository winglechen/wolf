package study.daydayup.wolf.business.pay.api.dto.base.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Response;

/**
 * study.daydayup.wolf.business.pay.api.dto.base.subscribe
 *
 * @author Wingle
 * @since 2020/4/29 11:08 上午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeResponse implements Response {
    private Integer code;
    private String msg;
}
