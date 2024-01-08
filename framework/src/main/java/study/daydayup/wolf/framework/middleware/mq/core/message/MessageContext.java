package study.daydayup.wolf.framework.middleware.mq.core.message;

import lombok.Data;
import study.daydayup.wolf.common.lang.contract.Context;

import java.io.Serializable;

/**
 * study.daydayup.wolf.framework.middleware.mq.domain.context
 *
 * @author Wingle
 * @since 2021/11/30 下午10:48
 **/
@Data
public class MessageContext implements Context, Serializable {
    private static final long serialVersionUID = -3341601836668852474L;
    /**
     * Please note that it may not be global unique.
     */
    private String mqId;
    private int shardId;
    private long offset;
    private long timestamp;
    private int reconsumeTimes;
}