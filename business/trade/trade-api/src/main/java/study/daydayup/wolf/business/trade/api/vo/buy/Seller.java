package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.VO;

/**
 * study.daydayup.wolf.business.trade.api.vo.buy
 *
 * @author Wingle
 * @since 2019/12/13 3:22 下午
 **/
@Data
public class Seller extends VO {
    private long id;
    private String name;
    private String logo;
}
