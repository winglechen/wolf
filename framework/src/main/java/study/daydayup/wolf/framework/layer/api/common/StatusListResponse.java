package study.daydayup.wolf.framework.layer.api.common;

import lombok.Data;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.api.Response;

import java.util.ArrayList;
import java.util.List;

@Data
public class StatusListResponse implements Response {
    private final List<String> success = new ArrayList<>();
    private final List<String> failure = new ArrayList<>();

    public void success(String status) {
        success.add(status);
    }

    public void failure(String status) {
        failure.add(status);
    }

    public boolean hasFailure() {
        return CollectionUtil.notEmpty(failure);
    }

}
