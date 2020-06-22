package study.daydayup.wolf.framework.rpc.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest implements Request {
    @NotNull @Min(1)
    private Integer pageSize;
    // prePage: -1; nextPage: 1;
    @NotNull @Min(1)
    private Integer pageNum;

    private Integer order;
    private String orderBy;
    private Long orderValue;
}
