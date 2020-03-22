package study.daydayup.wolf.business.uc.api.crm.staff.dto;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.uc.api.crm.customer.info.dto
 *
 * @author Wingle
 * @since 2020/3/22 9:04 下午
 **/
@Data
public class StaffId implements Request {
    private Long accountId;
    private Long orgId;
}
