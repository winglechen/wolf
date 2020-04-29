package study.daydayup.wolf.business.pay.api.dto.base.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Map;

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
public class SubscribeRequest implements Request {
    @NotNull @Positive
    private Integer paymentMethod;
    private Map<String, Object> header;

    private String data;
}
