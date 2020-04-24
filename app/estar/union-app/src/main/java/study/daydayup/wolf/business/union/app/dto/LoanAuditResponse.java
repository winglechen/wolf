package study.daydayup.wolf.business.union.app.dto;

import lombok.Data;
import study.daydayup.wolf.business.goods.api.entity.Goods;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.framework.layer.api.Response;

/**
 * study.daydayup.wolf.business.union.app.dto
 *
 * @author Wingle
 * @since 2020/4/24 9:54 下午
 **/
@Data
public class LoanAuditResponse implements Response {
    private Contract contract;
    private Goods goods;
}
