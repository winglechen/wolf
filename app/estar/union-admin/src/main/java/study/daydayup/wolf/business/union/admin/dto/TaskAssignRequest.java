package study.daydayup.wolf.business.union.admin.dto;

import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * study.daydayup.wolf.business.union.admin.dto
 *
 * @author Wingle
 * @since 2020/3/17 6:56 下午
 **/
@Data
public class TaskAssignRequest implements Request {
    @NonNull @Positive
    private Long staffId;
    private Long taskId;
    private List<Long> taskIds;

    public void valid() {
        if (null == staffId) {
            throw new IllegalArgumentException("staffId can't be null");
        }

        if (null == taskId && CollectionUtil.isEmpty(taskIds)) {
            throw new IllegalArgumentException("taskId and TaskIds can't all be null");
        }
    }
}
