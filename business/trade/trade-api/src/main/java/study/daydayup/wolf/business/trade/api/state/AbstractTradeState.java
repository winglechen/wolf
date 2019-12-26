package study.daydayup.wolf.business.trade.api.state;

import lombok.Setter;
import study.daydayup.wolf.business.trade.api.exception.InvalidTradeStateException;

import java.time.LocalDateTime;


/**
 * study.daydayup.wolf.business.trade.api.state
 *
 * @author Wingle
 * @since 2019/10/10 11:26 上午
 **/
@Setter
public abstract class AbstractTradeState implements TradeState {
    protected final String STATE_NAMESPACE_PREFIX = "study.daydayup.wolf.business.trade.api";

    protected Integer code;
    protected String name;
    protected LocalDateTime updatedAt;

    @Override
    public Integer getCode() {
        if (code <= 0) {
            throw new InvalidTradeStateException(code);
        }

        return code;
    }

    @Override
    public String getName() {
        return null;
    }



    @Override
    public LocalDateTime getUpdatedAt() {
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }

        return updatedAt;
    }

}
