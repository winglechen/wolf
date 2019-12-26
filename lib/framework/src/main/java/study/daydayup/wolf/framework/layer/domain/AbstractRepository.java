package study.daydayup.wolf.framework.layer.domain;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.framework.layer.domain
 *
 * @author Wingle
 * @since 2019/12/25 4:06 下午
 **/
public abstract class AbstractRepository {
    @Resource
    @Qualifier("trade")
    protected EventBus eventBus;

    protected void fire(List<Event> eventList) {
        if (eventList == null || eventList.isEmpty()) {
            return;
        }

        for (Event event: eventList) {
            eventBus.post(event);
        }
    }
}
