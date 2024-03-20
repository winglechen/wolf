package com.onedot.win.framework.layer.domain;

import com.onedot.win.framework.layer.api.AbstractModel;

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
