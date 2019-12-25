package study.daydayup.wolf.framework.layer.domain;

import lombok.Data;

/**
 * study.daydayup.wolf.framework.layer.domain
 *
 * @author Wingle
 * @since 2019/12/25 4:05 下午
 **/
@Data
public abstract class AbstractEntity<T> implements Entity {
    protected boolean isNew = false;

    protected T model;
    protected T changes;
    protected T locker;
}
