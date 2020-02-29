package study.daydayup.wolf.business.pay.biz.domain.repository;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.entity.PaymentLog;
import study.daydayup.wolf.business.pay.biz.dal.dao.PaymentLogDAO;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.PaymentLogDO;
import study.daydayup.wolf.framework.layer.context.RpcContext;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.domain.repository
 *
 * @author Wingle
 * @since 2020/2/29 6:37 下午
 **/
@Component
public class PaymentLogRepository extends AbstractRepository implements Repository {
    @Resource
    private PaymentLogDAO dao;
    @Resource
    private RpcContext context;

    public int add(@NonNull PaymentLog log) {
        PaymentLogDO logDO = new PaymentLogDO();
        BeanUtils.copyProperties(log, logDO);

        logDO.setCreatedAt(context.getRequestTime());
        return dao.insertSelective(logDO);
    }
}
