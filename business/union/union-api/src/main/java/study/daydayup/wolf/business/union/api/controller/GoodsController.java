package study.daydayup.wolf.business.union.api.controller;

import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.goods.api.entity.Goods;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.union.api.controller
 *
 * @author Wingle
 * @since 2019/12/11 6:59 下午
 **/
@RestController
@RequestMapping("/api/v1")
public class GoodsController {
    @PostMapping("/goods")
    public Result create(@RequestBody Goods goods) {
        return Result.ok();
    }

    @GetMapping("/goods/{goodsId}")
    public Result findById(@RequestParam("goodsId") long goodsId) {
        return Result.ok();
    }

    @GetMapping("/goods")
    public Result findByOrgId() {
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
