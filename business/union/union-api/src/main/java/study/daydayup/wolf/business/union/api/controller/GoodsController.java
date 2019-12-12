package study.daydayup.wolf.business.union.api.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.account.auth.agent.exception.SessionNotFoundException;
import study.daydayup.wolf.business.goods.api.entity.Goods;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.business.goods.api.service.LoanService;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.union.api.config.GoodsConfig;
import study.daydayup.wolf.business.union.api.config.LoanConfig;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * study.daydayup.wolf.business.union.api.controller
 *
 * @author Wingle
 * @since 2019/12/11 6:59 下午
 **/
@RestController
@RequestMapping("/api/v1")
public class GoodsController extends Controller {
    @Reference
    private LoanService loanService;
    @Resource
    private GoodsConfig goodsConfig;
    @Resource
    private LoanConfig loanConfig;
    @Resource
    private Session session;

    @PostMapping("/goods")
    public Result create(@Valid @RequestBody LoanGoods goods) {
        BeanUtils.copyProperties(goodsConfig, goods);
        goods.setId(0);

        Loan loan = goods.getLoan();
        BeanUtils.copyProperties(loanConfig, loan);
        goods.setLoan(loan);

        loanService.create(goods);
        return Result.ok();
    }

    @GetMapping("/goods/{goodsId}")
    public Result<LoanGoods> findById(@RequestParam("goodsId") long goodsId) {
        if (goodsId <= 0) {
            throw new IllegalArgumentException("Invalid goodsId: " + goodsId);
        }

        Long orgId = (Long)session.get("orgId");
        if (orgId == null) {
            throw new SessionNotFoundException("orgId");
        }

        LoanGoods goods = loanService.findById(goodsId, orgId);
        return Result.ok(goods);
    }

    @GetMapping("/goods")
    public Result<List<LoanGoods>> findByOrgId() {
        return Result.ok();
    }

    @PostMapping("/goods/search")
    public Result search() {
        return Result.ok();
    }

    @PutMapping("/goods")
    public Result modify(@RequestBody Goods goods) {
        return Result.ok();
    }

    @DeleteMapping("/goods/{goodsId}")
    public Result remove(@RequestParam("goodsId") long goodsId) {
        return Result.ok();
    }

    @PutMapping("/goods/{goodsId}")
    public Result listing(@RequestParam("goodsId") long goodsId) {
        return Result.ok();
    }

    @PutMapping("/goods/{goodsId}")
    public Result delisting(@RequestParam("goodsId") long goodsId) {
        return Result.ok();
    }
}
