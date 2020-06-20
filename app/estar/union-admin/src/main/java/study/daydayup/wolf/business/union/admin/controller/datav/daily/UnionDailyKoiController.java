package study.daydayup.wolf.business.union.admin.controller.datav.daily;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyAudit;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyKoi;
import study.daydayup.wolf.bigdata.datav.api.service.daily.DailyKoiService;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.common.util.time.DateUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * study.daydayup.wolf.business.union.admin.controller.datav.daily
 *
 * @author Wingle
 * @since 2020/3/25 11:43 下午
 **/
@RestController
public class UnionDailyKoiController {
    @Reference
    private DailyKoiService koiService;
    @Resource
    private Session session;

    @GetMapping("/datav/daily/koi")
    public Result<Page<DailyKoi>> findByDate(
            @RequestParam(value = "startDate", required=false ) String startDate,
            @RequestParam(value = "endDate", required=false ) String endDate,
            @RequestParam(value = "pageNum", required=false ) Integer pageNum) {

        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();


        DateRangeRequest request = DateRangeRequest.builder()
                .orgId(orgId)
                .build();

        if (null != startDate) {
            request.setStartDate(LocalDate.parse(startDate, DateUtil.DEFAULT_DATE_FORMATTER));
        }

        if (null != endDate) {
            request.setEndDate(LocalDate.parse(endDate, DateUtil.DEFAULT_DATE_FORMATTER));
        }

        return koiService.findByRange(request, pageRequest);
    }

    @GetMapping("/datav/daily/audit")
    public Result<Page<DailyAudit>> findAuditByDate(
            @RequestParam(value = "date", required=false ) String date,
            @RequestParam(value = "startDate", required=false ) String startDate,
            @RequestParam(value = "endDate", required=false ) String endDate,
            @RequestParam(value = "pageNum", required=false ) Integer pageNum) {

        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();


        DateRangeRequest request = DateRangeRequest.builder()
                .orgId(orgId)
                .build();

        if (null != date) {
            request.setDate(LocalDate.parse(date, DateUtil.DEFAULT_DATE_FORMATTER));
        }
        if (null != startDate) {
            request.setStartDate(LocalDate.parse(startDate, DateUtil.DEFAULT_DATE_FORMATTER));
        }
        if (null != endDate) {
            request.setEndDate(LocalDate.parse(endDate, DateUtil.DEFAULT_DATE_FORMATTER));
        }

        return koiService.findAuditByRange(request, pageRequest);
    }


}
