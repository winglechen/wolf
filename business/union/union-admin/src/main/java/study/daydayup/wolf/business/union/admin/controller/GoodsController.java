package study.daydayup.wolf.business.union.admin.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.business.goods.api.service.GoodsService;
import study.daydayup.wolf.business.goods.api.service.LoanGoodsService;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.union.admin.config.GoodsConfig;
import study.daydayup.wolf.business.union.admin.config.LoanConfig;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.PageRequest;
import study.daydayup.wolf.framework.rpc.page.Page;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.api.controller
 *
 * @author Wingle
 * @since 2019/12/11 6:59 下午
 **/
@RestController
@RequestMapping("/api/v1")
public class GoodsController extends BaseController {
    @Reference
    private LoanGoodsService loanGoodsService;
    @Reference
    private GoodsService goodsService;
    @Resource
    private GoodsConfig goodsConfig;
    @Resource
    private LoanConfig loanConfig;
    @Resource
    private Session session;

    @PostMapping("/goods")
    public Result create(@Validated @RequestBody LoanGoods goods) {
        BeanUtils.copyProperties(goodsConfig, goods);

        Loan loan = goods.getLoan();
        BeanUtils.copyProperties(loanConfig, loan);
        goods.setLoan(loan);

        goods.setId(null);
        goods.setOrgId(getFromSession("orgId", Long.class));

        loanGoodsService.create(goods);
        return Result.ok();
    }

    @GetMapping("/goods/{goodsId}")
    public Result<LoanGoods> findById(@PathVariable("goodsId") Long goodsId) {
        if (null == goodsId || goodsId <= 0) {
            throw new IllegalArgumentException("Invalid goodsId: " + goodsId);
        }

        Long orgId = getFromSession("orgId", Long.class);
        LoanGoods goods = loanGoodsService.findById(goodsId, orgId);

        return Result.ok(goods);
    }

    @GetMapping("/goods/one")
    public Result<LoanGoods> findOneByOrgId() {
        Long orgId = getFromSession("orgId", Long.class);
        LoanGoods goods = loanGoodsService.findOneByOrgId(orgId);

        return Result.ok(goods);
    }

    @GetMapping("/goods")
    public Result<Page<LoanGoods>> findByOrgId(@RequestParam(value = "page", required = false) Integer page) {
        Long orgId = getFromSession("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == page ? 1 : page)
                .pageSize(3)
                .build();

        Page<LoanGoods> goods = loanGoodsService.findByOrgId(orgId, pageRequest);
        return Result.ok(goods);
    }

    @PostMapping("/goods/search")
    public Result search() {
        return Result.ok();
    }

    @PutMapping("/goods")
    public Result modify(@Validated @RequestBody LoanGoods goods) {
        Long orgId = getFromSession("orgId", Long.class);
        goods.setOrgId(orgId);

        loanGoodsService.modify(goods);
        return Result.ok();
    }

    @DeleteMapping("/goods/{goodsId}")
    public Result remove(@PathVariable("goodsId") Long goodsId) {
        if (null == goodsId || goodsId <= 0) {
            throw new IllegalArgumentException("Invalid goodsId: " + goodsId);
        }

        Long orgId = getFromSession("orgId", Long.class);
        goodsService.remove(goodsId, orgId);
        return Result.ok();
    }

    @PutMapping("/goods/listing/{goodsId}")
    public Result listing(@PathVariable("goodsId") Long goodsId) {
        if (null == goodsId || goodsId <= 0) {
            throw new IllegalArgumentException("Invalid goodsId: " + goodsId);
        }

        Long orgId = getFromSession("orgId", Long.class);

        goodsService.delistingAll(orgId);
        goodsService.listing(goodsId, orgId);

        return Result.ok();
    }

    @PutMapping("/goods/delisting/{goodsId}")
    public Result delisting(@PathVariable("goodsId") Long goodsId) {
        if (null == goodsId || goodsId <= 0) {
            throw new IllegalArgumentException("Invalid goodsId: " + goodsId);
        }

        Long orgId = getFromSession("orgId", Long.class);
        goodsService.delisting(goodsId, orgId);

        return Result.ok();
    }
}
