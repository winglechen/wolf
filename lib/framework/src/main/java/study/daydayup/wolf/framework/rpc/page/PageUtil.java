package study.daydayup.wolf.framework.rpc.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * study.daydayup.wolf.framework.rpc.page
 *
 * @author Wingle
 * @since 2020/1/5 12:23 上午
 **/
@Deprecated
public class PageUtil<T> {
    private com.github.pagehelper.Page<T> hPage;

    public static <T> PageUtil<T> startPage(int pageNum, int pageSize) {
        com.github.pagehelper.Page<T> hPage = PageHelper.startPage(pageNum, pageSize);

        return new PageUtil<T>(hPage);
    }

    public static <T> Page<T> of(PageInfo<T> pageInfo) {
        if (pageInfo == null) {
            return null;
        }

        return Page.<T>builder()
                .data(pageInfo.getList())
                .total(pageInfo.getTotal())
                .pageSize(pageInfo.getPageSize())
                .pages(pageInfo.getPages())
                .pageNum(pageInfo.getPageNum())
                .hasNextPage(pageInfo.isHasNextPage())
                .hasPrePage(pageInfo.isHasNextPage())
                .build();
    }


    public PageUtil(com.github.pagehelper.Page<T> hPage) {
        this.hPage = hPage;
    }

    public Page<T> getPage() {
        return Page.of(hPage);
    }
}
