package study.daydayup.wolf.framework.layer.domain;

import study.daydayup.wolf.framework.layer.api.AbstractModel;

/**
 * @author weixing
 * @since 2022/10/13 19:01
 */
public interface ImmutableEntity extends Entity {
    default void initChangesHolder() {
    }

    default <T extends AbstractModel> EntityChangesHolder<T> getEntityChangesHolder() {
        return null;
    }
}
