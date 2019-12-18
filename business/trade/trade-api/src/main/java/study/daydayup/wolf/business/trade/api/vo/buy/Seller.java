package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.domain.VO;

/**
 * study.daydayup.wolf.business.trade.api.vo.buy
 *
 * @author Wingle
 * @since 2019/12/13 3:22 下午
 **/
@Data
@Builder
@NoArgsConstructor
public class Seller extends VO {
    private long id;
    private String name;
    private String logo;
}
