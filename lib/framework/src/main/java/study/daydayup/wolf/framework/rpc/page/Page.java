package study.daydayup.wolf.framework.rpc.page;

import com.github.pagehelper.PageHelper;
import lombok.*;
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
    private Object attachment;

    private Long total;
    private Integer pageSize;
    private Integer pages;

    private Integer pageNum;
    private Boolean hasNextPage;
    private Boolean hasPrePage;

    private OrderEnum order;
    private String orderBy;
    private Long orderValue;

    public static void nextPage(@NonNull PageOrder order, int pageSize) {
        PageUtil.nextPage(order, pageSize);
    }

    public static void startPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
    }

    public static void offsetPage(int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
    }

    public static <T> Page<T> of(List<T> list) {
        if (list == null || list.isEmpty()) {
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

        return Page.<T>builder()
                .total(pageInfo.getTotal())
                .pageSize(pageInfo.getPageSize())
                .pages(pageInfo.getPages())
                .hasNextPage(pageInfo.getPages() > pageInfo.getPageNum())
                .hasPrePage(pageInfo.getPages() > 0 && pageInfo.getPageNum() > 1)
                .pageNum(pageInfo.getPageNum())
                .build();
    }

    public static <T> Page<T> empty() {
        return empty(1, 10);
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

    public static <T> Page<T> one(T t) {
        List<T> tList = new ArrayList<>();
        tList.add(t);

        return one(tList);
    }

    public static <T> Page<T> one(List<T> data) {
        int total = data.size();

        return Page.<T>builder()
                .data(data)
                .total((long) total)
                .pageSize(total)
                .pages(1)
                .pageNum(1)
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
