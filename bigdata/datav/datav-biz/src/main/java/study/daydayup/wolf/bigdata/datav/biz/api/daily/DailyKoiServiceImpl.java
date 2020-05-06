package study.daydayup.wolf.bigdata.datav.biz.api.daily;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyAudit;
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
    public Result<Page<DailyKoi>> findByRange(@Validated DateRangeRequest request, @NonNull PageRequest pageReq) {
        Page<DailyKoi> result = selectByRange(request, pageReq);
        return Result.ok(result);
    }

    @Override
    public Result<Page<DailyAudit>> findAuditByRange(@Validated DateRangeRequest request, @NonNull PageRequest pageReq) {
        Page<DailyAudit> auditPage;

        Page<DailyKoi> koiPage = selectByRange(request, pageReq);
        if (CollectionUtil.isEmpty(koiPage.getData())) {
            auditPage = Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
            return Result.ok(auditPage) ;
        }

        List<DailyAudit> auditList = DailyKoiConverter.toAudit(koiPage.getData());
        mergeAuditTradeStatistics(auditList, request);
        auditPage = koiPage.to(auditList);

        return Result.ok(auditPage) ;
    }

    private void mergeAuditTradeStatistics(@NonNull List<DailyAudit> auditList,@NonNull DateRangeRequest request) {

    }

    private Page<DailyKoi> selectByRange(@Validated DateRangeRequest request, @NonNull PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<DailyKoiDO> koiDOList = koiDAO.selectByDate(request);
        if (CollectionUtil.isEmpty(koiDOList)) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        List<DailyKoi> koiList = DailyKoiConverter.toModel(koiDOList);
        return Page.of(koiDOList).to(koiList);
    }
}
