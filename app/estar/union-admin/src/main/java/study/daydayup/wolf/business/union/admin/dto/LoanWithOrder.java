package study.daydayup.wolf.business.union.admin.dto;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.framework.layer.api.DTO;

import java.util.List;

/**
 * study.daydayup.wolf.business.union.admin.dto
 *
 * @author Wingle
 * @since 2020/3/9 6:22 下午
 **/
@Data
public class LoanWithOrder implements DTO {
    private Contract contract;
    private List<Order> loanOrderList;
    private List<Order> repayOrderList;
}
