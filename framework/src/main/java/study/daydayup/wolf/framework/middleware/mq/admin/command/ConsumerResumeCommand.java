package study.daydayup.wolf.framework.middleware.mq.admin.command;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.DTO;

/**
 * @author weixing
 * @since 2022/11/22 16:14
 */
@Data
public class ConsumerResumeCommand implements DTO {
    private String name;
}
