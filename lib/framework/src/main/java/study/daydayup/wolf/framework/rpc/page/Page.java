package study.daydayup.wolf.framework.rpc.page;

import com.github.pagehelper.PageInfo;
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
public class Page<T> implements Serializable {
    private List<T> list;

    private Long total;
    private Integer pageSize;
    private Integer pageNum;

    private Integer currentPage;
    private Boolean hasNextPage;
    private Boolean hasPrePage;

    private PageOrder pageOrder;

    public static void main(String[] args) {
        PageInfo<List<String>> pageInfo= new PageInfo<>();
    }
}
