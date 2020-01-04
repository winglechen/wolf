package study.daydayup.wolf.framework.rpc.page;

import com.github.pagehelper.PageInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.common.io.enums.OrderEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.framework.rpc
 *
 * @author Wingle
 * @since 2020/1/4 4:27 下午
 **/
@Data
@Builder
@NoArgsConstructor
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

    public static <T> Page<T> of(com.github.pagehelper.Page<T> pageInfo) {
        if (pageInfo == null) {
            return null;
        }

        Page<T> page = Page.<T>builder()
                .total(pageInfo.getTotal())
                .pageSize(pageInfo.getPageSize())
                .pages(pageInfo.getPages())
                .pageNum(pageInfo.getPageNum())
                .build();

        if (pageInfo.getPages() > pageInfo.getPageNum()) {
            page.setHasNextPage(true);
        } else {
            page.setHasNextPage(false);
        }
        if (pageInfo.getPages() > 0 && pageInfo.getPageNum() > 1) {
            page.setHasPrePage(true);
        } else {
            page.setHasPrePage(false);
        }
        return page;
    }

    public static <T> Page<T> empty(Integer pageNum, Integer pageSize) {
        return Page.<T>builder()
                .list(new ArrayList<>())
                .total(0L)
                .pageSize(pageSize)
                .pages(0)
                .pageNum(pageNum)
                .hasNextPage(false)
                .hasPrePage(false)
                .build();
    }

    public <M> Page<M> to(List<M> list) {
        Page<M> newPage = new Page<>();
        BeanUtils.copyProperties(this, newPage);

        newPage.setList(list);
        return newPage;
    }

}
