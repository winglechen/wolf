package study.daydayup.wolf.business.trade.api.domain.vo.buy;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.VO;

import javax.validation.constraints.Min;

/**
 * study.daydayup.wolf.business.trade.api.domain.entity.buy
 *
 * @author Wingle
 * @since 2019/10/5 1:36 PM
 **/
@Data
public class Buyer implements VO  {
    @Min(1)
    private Long  id;
    private String name;
    private String avatar;
}
