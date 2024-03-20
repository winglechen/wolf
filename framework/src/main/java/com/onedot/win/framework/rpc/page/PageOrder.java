package com.onedot.win.framework.rpc.page;

import lombok.Data;
import com.onedot.win.common.io.enums.OrderEnum;

import java.io.Serializable;

/**
 * com.onedot.win.framework.rpc.page
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
