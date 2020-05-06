package study.daydayup.wolf.bigdata.datav.api.service.daily;

import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyAudit;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyKoi;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.bigdata.datav.api.service.daily
 *
 * @author Wingle
 * @since 2020/3/25 11:56 下午
 **/
public interface DailyKoiService extends Service {
    Result<Page<DailyKoi>> findByRange(DateRangeRequest request, PageRequest pageReq);
    Result<Page<DailyAudit>> findAuditByRange(DateRangeRequest request, PageRequest pageReq);
}
