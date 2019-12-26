package study.daydayup.wolf.framework.layer.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.framework.layer.domain
 *
 * @author Wingle
 * @since 2019/12/25 4:05 下午
 **/
@Data
public abstract class AbstractEntity<T> implements Entity {
    protected boolean isNew = true;

    protected T model;
    protected T changes;
    protected T locker;

    protected List<Event> eventList = new ArrayList<>();

    protected void fire(Event event) {
        eventList.add(event);
    }
}
