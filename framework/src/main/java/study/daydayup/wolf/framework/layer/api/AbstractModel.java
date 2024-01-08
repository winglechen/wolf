package study.daydayup.wolf.framework.layer.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author weixing
 * @since 2022/9/23 15:42
 */
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractModel implements Model {
    protected Integer version;
}