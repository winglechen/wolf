package study.daydayup.wolf.business.union.app.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum;
import study.daydayup.wolf.business.goods.api.service.GoodsService;
import study.daydayup.wolf.business.goods.api.service.LoanGoodsService;

import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;


/**
 * study.daydayup.wolf.business.union.api.controller
 *
 * @author Wingle
 * @since 2019/12/11 6:59 下午
 **/
@RestController
public class UnionGoodsController extends BaseUnionController {
    private static final int DEFAULT_GOODS_TYPE = GoodsTypeEnum.LOAN.getCode();
    @Reference
    private LoanGoodsService loanGoodsService;
    @Reference
    private GoodsService goodsService;


    @GetMapping("/goods/detail/{goodsId}")
    public Result<LoanGoods> findById(@PathVariable("goodsId") Long goodsId) {
        if (null == goodsId || goodsId <= 0) {
            throw new IllegalArgumentException("Invalid goodsId: " + goodsId);
        }

        Long orgId = getFromSession("orgId", Long.class);
        LoanGoods goods = loanGoodsService.findById(goodsId, orgId);

        return Result.ok(goods);
    }

    @GetMapping("/goods/one")
    public Result<LoanGoods> findOneByOrgId(@RequestParam(value = "goodsType", required = false) Integer goodsType) {
        Long orgId = getFromSession("orgId", Long.class);
        if (goodsType == null) {
            goodsType = DEFAULT_GOODS_TYPE;
        }

        LoanGoods goods = loanGoodsService.findOneByOrgId(orgId, goodsType);

        return Result.ok(goods);
    }

    @GetMapping("/auth/loan/one")
    public Result<LoanGoods> findOneLoanByOrgId(@RequestParam(value = "orgId") Long orgId) {
        LoanGoods goods = loanGoodsService.findOneByOrgId(orgId, DEFAULT_GOODS_TYPE);

        return Result.ok(goods);
    }


    @GetMapping("/goods/list")
    public Result<Page<LoanGoods>> findByOrgId(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = getFromSession("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        Page<LoanGoods> goods = loanGoodsService.findByOrgId(orgId, pageRequest);
        return Result.ok(goods);
    }

    @PostMapping("/goods/search")
    public Result<Object> search() {
        return Result.ok();
    }



}
