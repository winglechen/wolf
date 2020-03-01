package study.daydayup.wolf.business.trade.order.biz.domain.factory;

import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.order.biz.domain.entity.ContractEntity;
import study.daydayup.wolf.framework.layer.domain.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.factory
 *
 * @author Wingle
 * @since 2020/3/1 3:13 下午
 **/
public class ContractFactory implements Factory {
    public static ContractEntity create(Contract model) {
        ContractEntity entity = new ContractEntity(model, null);
        entity.init();

        return entity;
    }

    public static List<ContractEntity> create(List<Contract> modelList) {
        List<ContractEntity> entityList = new ArrayList<>(modelList.size());
        for (Contract model : modelList) {
            entityList.add(create(model));
        }

        return entityList;
    }
}
