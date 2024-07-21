package com.wolf.framework.layer.dal.page;

import com.wolf.common.io.enums.OrderEnum;
import java.io.Serializable;
import lombok.Data;

/**
 * com.wolf.framework.rpc.page
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
