package study.daydayup.wolf.framework.layer.domain;

import lombok.Data;
import study.daydayup.wolf.common.util.lang.BeanUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.framework.layer.domain
 *
 * @author Wingle
 * @since 2019/12/25 4:05 下午
 **/
@Data
public abstract class AbstractEntityV1<T> implements EntityV1 {
    protected boolean isNew = true;

    protected T model;
    protected T changes;
    protected T key;
    protected LocalDateTime now;

    protected List<Event> eventList = new ArrayList<>();

    protected void fire(Event event) {
        eventList.add(event);
    }

    protected boolean tryFire(Event event) {
        return false;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    protected void copyChangesToModel() {
        BeanUtil.copyProperties(changes, model, true);
    }
}
