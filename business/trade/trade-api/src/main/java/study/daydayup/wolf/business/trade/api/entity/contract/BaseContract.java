package study.daydayup.wolf.business.trade.api.entity.contract;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.entity.Trade;

/**
 * study.daydayup.wolf.business.trade.api.entity.contract
 *
 * @author Wingle
 * @since 2019/12/16 2:42 下午
 **/
@Data
@SuperBuilder(toBuilder = true)
public class BaseContract extends Trade {

}
