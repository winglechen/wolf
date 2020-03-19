package study.daydayup.wolf.business.org.api.task.dto.request.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.org.api.task.dto.TaskOwner;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.api.Request;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * study.daydayup.wolf.business.org.api.task.dto.request.task
 *
 * @author Wingle
 * @since 2020/3/16 12:27 上午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class TaskTypeRequest extends TaskOwner implements Request {
    private Integer state;
    private Set<Integer> stateSet;
    private Integer taskType;
    private Integer projectId;

    public void addState(Integer state) {
        if (state == null) {
            return;
        }

        initStateSet();
        stateSet.add(state);
    }

    public void addStates(Collection<Integer> states) {
        if (CollectionUtil.isEmpty(states)) {
            return;
        }
        initStateSet();
        stateSet.addAll(states);
    }

    private void initStateSet() {
        if (stateSet != null) {
            return;
        }
        stateSet = new TreeSet<>();
    }
}
