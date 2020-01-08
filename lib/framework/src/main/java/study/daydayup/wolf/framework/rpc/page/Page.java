package study.daydayup.wolf.framework.rpc.page;

import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Serializable {
    private List<T> data;

    private Long total;
    private Integer pageSize;
    private Integer pages;

    private Integer pageNum;
    private Boolean hasNextPage;
    private Boolean hasPrePage;

    private OrderEnum order;
    private String orderBy;
    private Long orderValue;

    public static void nextPage(PageOrder order, int pageSize) {

    }

    public static void startPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
    }

    public static void offsetPage(int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
    }

    public static <T> Page<T> of(List<T> list) {
        if (list == null) {
            return empty(1, null);
        }

        if (list instanceof com.github.pagehelper.Page) {
            Page<T> newPage = of( (com.github.pagehelper.Page<T>)list);
            newPage.setData(list);
            return newPage;
        }

        throw new IllegalArgumentException("Invalid Page");
    }

    public static <T> Page<T> of(com.github.pagehelper.Page<T> pageInfo) {
        if (pageInfo == null) {
            return null;
        }

        Page<T> page = Page.<T>builder()
                .total(pageInfo.getTotal())
                .pageSize(pageInfo.getPageSize())
                .pages(pageInfo.getPages())
                .hasNextPage(pageInfo.getPages() > pageInfo.getPageNum())
                .hasPrePage(pageInfo.getPages() > 0 && pageInfo.getPageNum() > 1)
                .pageNum(pageInfo.getPageNum())
                .build();

        return page;
    }

    public static <T> Page<T> empty() {
        return empty(null, null);
    }

    public static <T> Page<T> empty(Integer pageNum, Integer pageSize) {
        return Page.<T>builder()
                .data(new ArrayList<>())
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

        newPage.setData(list);
        return newPage;
    }

}
