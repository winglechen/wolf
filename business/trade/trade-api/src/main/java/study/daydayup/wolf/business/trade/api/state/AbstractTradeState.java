package study.daydayup.wolf.business.trade.api.state;

import lombok.Data;
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
    protected int state;
    protected String name;
    protected LocalDateTime updatedAt;

    @Override
    public int getState() {
        if (state <= 0) {
            throw new InvalidTradeStateException(state);
        }

        return state;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }

        return updatedAt;
    }

}
