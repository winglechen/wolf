package study.daydayup.wolf.business.uc.api.crm.customer.info.dto;

import lombok.*;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.uc.api.crm.customer.info.dto
 *
 * @author Wingle
 * @since 2020/3/22 9:04 下午
 **/
@Data
@NoArgsConstructor
public class CustomerId implements Request {
    private Long accountId;
    private Long orgId;

    public CustomerId(@NonNull Long orgId, @NonNull Long accountId) {
        this.orgId = orgId;
        this.accountId = accountId;
    }

    public static String toId(@NonNull Long orgId, @NonNull Long accountId) {
        return new CustomerId(orgId, accountId).toString();
    }

    @Override
    public String toString() {
        return StringUtil.join(orgId, accountId);
    }
}
