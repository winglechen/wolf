package com.onedot.win.framework.layer.domain;

import lombok.Data;
import org.apache.commons.lang.ObjectUtils;
import com.onedot.win.common.util.lang.BeanUtil;
import com.onedot.win.framework.layer.api.AbstractModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author weixing
 * @since 2022/9/23 15:38
 */
@Data
public class EntityChangesHolder<T extends AbstractModel> {
    private T model;
    private T key;
    private T changes;
    protected List<Event> eventList = new ArrayList<>();

    public T model() {
        return model;
    }

    public T changes() {
        return changes;
    }

    public T key() {
        return key;
    }

    public void saveChanges(Consumer<T> consumer) {
        consumer.accept(changes);
    }

    public void saveKey(Consumer<T> consumer) {
        consumer.accept(key);
    }

    protected void registerEvent(Event event) {
        eventList.add(event);
    }

    public void mergeToEntityAndClearChanges() {
        BeanUtil.copyPropertiesDeep(changes, model);
        BeanUtil.copyPropertiesDeep(changes, key);
        updateEntityVersionAfterPersisted();
        updateKeyVersionAfterPersisted();
        changes = null;
    }

    public void copyChangesToModel() {
        BeanUtil.copyPropertiesDeep(this.changes, this.model, true);
    }

    /**
     * @return false: all the fields of changes are null. true: any field of changes is not null.
     */
    public boolean hasAnyChange(){
        if (changes == null) {
            return false;
        }

        Field[] fields = changes.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(changes);
            } catch (IllegalAccessException e) {
                continue;
            }
            if (value != null) {
                return true;
            }
        }

        return false;
    }

    protected void updateEntityVersionAfterPersisted() {
        if (model.getVersion() != null) {
            int version = nextVersion();
            model.setVersion(version);
        }
    }

    protected void updateKeyVersionAfterPersisted() {
        if (model.getVersion() != null) {
            key.setVersion(model.getVersion());
        }
    }

    protected int nextVersion() {
        return key.getVersion() + 1;
    }

}
