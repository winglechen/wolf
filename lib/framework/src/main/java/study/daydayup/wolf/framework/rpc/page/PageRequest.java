package study.daydayup.wolf.framework.rpc.page;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.framework.rpc.page
 *
 * @author Wingle
 * @since 2020/1/4 5:09 下午
 **/
@Data
public class PageRequest implements Request {
    private Integer pageSize;
    private Integer currentPage;

    private PageOrder pageOrder;
}
