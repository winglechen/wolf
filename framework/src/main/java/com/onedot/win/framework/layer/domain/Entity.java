package com.onedot.win.framework.layer.domain;

import com.onedot.win.framework.layer.api.AbstractModel;

import java.io.Serializable;

/**
 * @author weixing
 * @since 2022/9/23 20:22
 */
public interface Entity extends Serializable {
    default void initIdentity() {
    }

    default void resetIdentity() {
    }

    void initChangesHolder();

    <T extends AbstractModel> EntityChangesHolder<T> getEntityChangesHolder();

    default <T extends AbstractModel> T changes() {
        return (T) getEntityChangesHolder().changes();
    }

    default <T extends AbstractModel> T key() {
        return (T) getEntityChangesHolder().key();
    }

    default void refresh() {
        getEntityChangesHolder().mergeToEntityAndClearChanges();
    }
}