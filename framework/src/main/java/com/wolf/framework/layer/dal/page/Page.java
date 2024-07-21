package com.wolf.framework.layer.dal.page;

import com.github.pagehelper.PageHelper;
import com.wolf.common.io.enums.OrderEnum;
import com.wolf.common.util.collection.CollectionUtil;
import com.wolf.framework.layer.api.Response;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;

/**
 * com.wolf.framework.rpc
 *
 * @author Wingle
 * @since 2020/1/4 4:27 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Response {
    private List<T> data;
    private Object attachment;
    private String lastId;

    private Long total;
    private Integer pageSize;
    private Integer pages;

    private Integer pageNum;
    private Boolean hasNextPage;
    private Boolean hasPrePage;

    private OrderEnum order;
    private String orderBy;

    public static void nextPage(@NonNull PageOrder order, int pageSize) {
        PageUtil.nextPage(order, pageSize);
    }

    public static void startPage(int pageNum, int pageSize, boolean count) {
        PageHelper.startPage(pageNum, pageSize, count);
    }

    public static void startPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
    }

    public static void offsetPage(int offset, int limit, boolean count) {
        PageHelper.offsetPage(offset, limit, count);
    }

    public static void offsetPage(int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
    }

    public static <T> Page<T> of(List<T> list) {
        if (list == null || list.isEmpty()) {
            return empty(1, null);
        }

        if (list instanceof com.github.pagehelper.Page) {
            Page<T> newPage = of((com.github.pagehelper.Page<T>) list);
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

    public static <T> Page<T> of(PageRequest pageRequest, Long total, List<T> data) {
        return of(pageRequest.getPageNum(), pageRequest.getPageSize(), total, data);
    }

    public static <T> Page<T> of(Integer pageNum, Integer pageSize, Long total, List<T> data) {
        if (CollectionUtil.isEmpty(data)) {
            return empty(pageNum, pageSize);
        }

        int pages = total <= pageSize ? 1 : (int) Math.ceil((double) total / pageSize);
        return Page.<T>builder()
                .data(data)
                .total(total)
                .pageSize(pageSize)
                .pages(pages)
                .pageNum(pageNum)
                .hasNextPage(pageNum < pages)
                .hasPrePage(pageNum > 1)
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
        if (t != null) {
            tList.add(t);
        }

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
