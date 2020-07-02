package study.daydayup.wolf.business.pay.api.dto.base.pay;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:53 下午
 **/
@Data
@Builder
public class PaymentCreateRequest implements Request {
    private Integer paymentMethod;

    private String tradeNo;
    private String paymentNo;
    private String paymentToken;
    private boolean duplicateCheck;

    @NotNull @Min(1)
    private Long payerId;
    private String payerName;
    @NotNull @Min(1)
    private Long payeeId;
    private String payeeName;

    @NotNull @DecimalMin("0.01")
    private BigDecimal amount;
    @NotNull
    private Integer currency;

    private Long goodsId;
    private String goodsName;
    private String goodsDescription;

    private String returnUrl;
    private String notifyUrl;

    private Map<String, Object> args;
    private String tags;

    private void initArgs() {
        if (args != null) {
            return;
        }

        args = new HashMap<>(8);
    }

    public Object get(@NonNull String key) {
        initArgs();
        return args.get(key);
    }

    public void put(@NonNull String key, Object value) {
        initArgs();
        args.put(key, value);
    }

    public void putAll(@NonNull Map<String, Object> kvs) {
        initArgs();
        args.putAll(kvs);
    }




}
