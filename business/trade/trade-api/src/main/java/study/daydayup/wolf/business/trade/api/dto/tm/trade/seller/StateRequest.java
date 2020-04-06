package study.daydayup.wolf.business.trade.api.dto.tm.trade.seller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.SellerRequest;
import study.daydayup.wolf.common.util.collection.ArrayUtil;
import study.daydayup.wolf.common.util.collection.CollectionUtil;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm.contract
 *
 * @author Wingle
 * @since 2020/1/14 10:51 上午
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class StateRequest extends SellerRequest {
    private Integer state;
    private Set<Integer> stateSet;

    private Integer tradeType;
    private LocalDateTime createdBefore;
    private LocalDateTime createdAfter;

    public void valid() {
        if (null == state && CollectionUtil.isEmpty(stateSet)) {
            throw new IllegalArgumentException("state and StateSet can't all be null");
        }
    }

    private void initStateSet() {
        if (stateSet != null) {
            return;
        }

        stateSet = new TreeSet<>();
    }

    public void add(@NonNull Integer... states) {
        if (ArrayUtil.isEmpty(states)) {
            return;
        }

        initStateSet();
        stateSet.addAll(Arrays.asList(states));
    }

    public void addAll(@NonNull Collection<Integer> states) {
        if (CollectionUtil.isEmpty(states)) {
            return;
        }

        initStateSet();
        stateSet.addAll(states);
    }
}
