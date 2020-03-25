package study.daydayup.wolf.bigdata.datav.biz.api.daily;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyKoi;
import study.daydayup.wolf.bigdata.datav.api.service.daily.DailyKoiService;
import study.daydayup.wolf.bigdata.datav.biz.converter.daily.DailyKoiConverter;
import study.daydayup.wolf.bigdata.datav.biz.dal.dao.DailyKoiDAO;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyKoiDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.bigdata.datav.biz.api.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:08 上午
 **/
@RpcService(protocol = "dubbo")
public class DailyKoiServiceImpl implements DailyKoiService {
    @Resource
    private DailyKoiDAO koiDAO;

    @Override
    public Result<Page<DailyKoi>> findByRange(@Validated DateRangeRequest request, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<DailyKoiDO> koiDOList = koiDAO.selectByDate(request);
        if (CollectionUtil.isEmpty(koiDOList)) {
            return Result.ok(
                    Page.empty(pageReq.getPageNum(), pageReq.getPageSize())
            );
        }

        List<DailyKoi> koiList = DailyKoiConverter.toModel(koiDOList);
        return Result.ok(Page.of(koiDOList).to(koiList));
    }
}
