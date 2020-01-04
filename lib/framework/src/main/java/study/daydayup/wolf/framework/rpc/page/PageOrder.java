package study.daydayup.wolf.framework.rpc.page;

import lombok.Data;
import study.daydayup.wolf.common.io.enums.OrderEnum;

import java.io.Serializable;

/**
 * study.daydayup.wolf.framework.rpc.page
 *
 * @author Wingle
 * @since 2020/1/4 5:13 下午
 **/
@Data
public class PageOrder implements Serializable {
    private OrderEnum order;
    private String orderBy;
    private Long orderValue;
}
