package study.daydayup.wolf.business.pay.api.dto.base;

import lombok.Data;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.model.type.string.Tag;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.pay.api.dto.base
 *
 * @author Wingle
 * @since 2020/3/1 1:04 上午
 **/
@Data
public class PaymentState implements Request {
    /**
     * key of state changing
     */
    private String paymentNo;

    private String outTradeNo;
    private Integer state;

    private Tag newTags;
    private ObjectMap newAttachment;

    /**
     * 覆盖式更新、慎用；尽量使用追加的方式
     */
    private String tags;
    private String attachment;

    private Integer version;


}
