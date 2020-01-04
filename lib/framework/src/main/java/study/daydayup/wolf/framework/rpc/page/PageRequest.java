package study.daydayup.wolf.framework.rpc.page;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.framework.rpc.page
 *
 * @author Wingle
 * @since 2020/1/4 5:09 下午
 **/
@Data
public class PageRequest implements Request {
    @NotNull @Min(1)
    private Integer pageSize;
    // prePage: -1; nextPage: 1;
    @NotNull
    private Integer pageNum;

    private Integer order;
    private String orderBy;
    private Long orderValue;
}
