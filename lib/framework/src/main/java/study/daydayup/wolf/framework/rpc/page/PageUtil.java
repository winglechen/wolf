package study.daydayup.wolf.framework.rpc.page;

import com.github.pagehelper.PageHelper;

/**
 * study.daydayup.wolf.framework.rpc.page
 *
 * @author Wingle
 * @since 2020/1/5 12:23 上午
 **/
public class PageUtil<T> {
    private com.github.pagehelper.Page<T> hPage;

    public static <T> PageUtil<T> startPage(int pageNum, int pageSize) {
        com.github.pagehelper.Page<T> hPage = PageHelper.startPage(pageNum, pageSize);

        return new PageUtil<T>(hPage);
    }

    public PageUtil(com.github.pagehelper.Page<T> hPage) {
        this.hPage = hPage;
    }

    public Page<T> getPage() {
        return Page.of(hPage);
    }
}
