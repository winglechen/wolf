package study.daydayup.wolf.business.trade.order.domain.entity;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.framework.layer.domain.Entity;

/**
 * study.daydayup.wolf.business.trade.order.domain.entity
 *
 * @author Wingle
 * @since 2019/10/7 11:54 下午
 **/
@Data
public class ContractEntity extends Entity {
    private Contract model;
    private Contract changes;
    private Contract rules;


}
