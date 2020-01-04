package study.daydayup.wolf.framework.rpc.page;

import com.github.pagehelper.PageInfo;
import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.common.io.enums.OrderEnum;

import java.io.Serializable;
import java.util.List;

/**
 * study.daydayup.wolf.framework.rpc
 *
 * @author Wingle
 * @since 2020/1/4 4:27 下午
 **/
@Data
@Builder
public class Page<T> implements Serializable {
    private List<T> list;

    private Long total;
    private Integer pageSize;
    private Integer pages;

    private Integer pageNum;
    private Boolean hasNextPage;
    private Boolean hasPrePage;

    private OrderEnum order;
    private String orderBy;
    private Long orderValue;

    public static <T> Page<T> of(PageInfo<T> pageInfo) {
        if (pageInfo == null) {
            return null;
        }

        return Page.<T>builder()
                .list(pageInfo.getList())
                .total(pageInfo.getTotal())
                .pageSize(pageInfo.getPageSize())
                .pages(pageInfo.getPages())
                .pageNum(pageInfo.getPageNum())
                .hasNextPage(pageInfo.isHasNextPage())
                .hasPrePage(pageInfo.isHasNextPage())
                .build();
    }

    public static void main(String[] args) {
        PageInfo<List<String>> pageInfo= new PageInfo<>();
    }
}
