package study.daydayup.wolf.demo.my.starter.ddd.trade.model;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

/**
 * study.daydayup.wolf.demo.my.starter.ddd.trade.model
 *
 * @author Wingle
 * @since 2019/12/25 4:34 下午
 **/
@Data
public class OrderLine implements Model {
    private String orderNo;

    private long goodsId;
    private String goodsName;
}
