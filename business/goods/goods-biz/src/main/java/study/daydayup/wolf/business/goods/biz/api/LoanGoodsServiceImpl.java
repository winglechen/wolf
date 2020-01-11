package study.daydayup.wolf.business.goods.biz.api;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.goods.api.dto.request.GoodsOption;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.business.goods.api.service.LoanGoodsService;
import study.daydayup.wolf.business.goods.biz.loan.LoanEntity;
import study.daydayup.wolf.business.goods.biz.loan.LoanGoodsRepository;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.biz.api
 *
 * @author Wingle
 * @since 2019/12/10 8:52 下午
 **/
@RpcService(protocol = "dubbo")
public class LoanGoodsServiceImpl implements LoanGoodsService {
    @Resource
    private LoanGoodsRepository repository;

    @Override
    public Long  create(LoanGoods goods) {
        LoanEntity entity = new LoanEntity();
        BeanUtils.copyProperties(goods, entity);

        return repository.save(entity);
    }

    @Override
    public int modify(LoanGoods goods) {
        LoanEntity entity = new LoanEntity();
        BeanUtils.copyProperties(goods, entity);

        repository.modify(entity);
        return 1;
    }

    @Override
    public LoanGoods findById(Long  goodsId, Long  orgId) {
        return findById(goodsId, orgId, null);
    }

    @Override
    public LoanGoods findById(Long  goodsId, Long  orgId, GoodsOption option) {
        return repository.findById(goodsId, orgId);
    }

    @Override
    public Page<LoanGoods> findByOrgId(Long  orgId, @Validated PageRequest pageRequest) {
        Page<LoanEntity> entityList = repository.findByOrgId(orgId, pageRequest);
        if (entityList.getData().isEmpty()) {
            return Page.empty();
        }

        List<LoanGoods> loanList = new  ArrayList<LoanGoods>(entityList.getData());
        return entityList.to(loanList);
    }

    @Override
    public LoanGoods findOneByOrgId(Long  orgId) {
        LoanEntity entity = repository.findOneByOrgId(orgId);
        if (null == entity) {
            return null;
        }

        return entity;
    }

    @Override
    public int remove(Long  goodsId, Long  orgId) {
        return 0;
    }
}
