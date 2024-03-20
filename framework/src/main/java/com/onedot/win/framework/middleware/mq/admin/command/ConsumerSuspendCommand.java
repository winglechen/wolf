package com.onedot.win.framework.middleware.mq.admin.command;

import lombok.Data;
import com.onedot.win.framework.layer.api.DTO;

/**
 * @author weixing
 * @since 2022/11/22 16:14
 */
@Data
public class ConsumerSuspendCommand implements DTO {
    private String name;
}
