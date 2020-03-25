package study.daydayup.wolf.business.union.admin.controller.datav.daily;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyKoi;
import study.daydayup.wolf.bigdata.datav.api.service.daily.DailyKoiService;
import study.daydayup.wolf.business.account.auth.agent.Session;
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
public class DailyKoiController {
    @Reference
    private DailyKoiService koiService;
    @Resource
    private Session session;

    @GetMapping("/datav/daily/koi/range")
    public Result<Page<DailyKoi>> findByDate(
            @RequestParam(value = "startDate", required=false ) LocalDate startDate,
            @RequestParam(value = "endDate", required=false ) LocalDate endDate,
            @RequestParam(value = "pageNum", required=false ) Integer pageNum) {

        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        DateRangeRequest request = DateRangeRequest.builder()
                .orgId(orgId)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        return koiService.findByRange(request, pageRequest);
    }
}
