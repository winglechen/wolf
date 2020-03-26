package study.daydayup.wolf.business.union.admin.controller.datav.track;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.bigdata.datav.api.dto.track.RepayRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.track.TrackRepay;
import study.daydayup.wolf.bigdata.datav.api.service.track.TrackRepayService;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.common.util.time.DateUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * study.daydayup.wolf.business.union.admin.controller.datav.track
 *
 * @author Wingle
 * @since 2020/3/25 11:43 下午
 **/
@RestController
public class LoanTrackController {
    @Reference
    private TrackRepayService repayService;
    @Resource
    private Session session;

    @GetMapping("/datav/track/repay")
    Result<Page<TrackRepay>> findTrack(@RequestParam(value = "startDate", required=false ) String startDate,
                                       @RequestParam(value = "endDate", required=false ) String endDate,
                                       @RequestParam(value = "goodsId", required=false) Long goodsId,
                                       @RequestParam(value = "installmentNo", required=false) Integer installmentNo,
                                       @RequestParam(value = "pageNum", required=false ) Integer pageNum) {

        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        RepayRequest repayRequest = initRepayRequest(goodsId, installmentNo, startDate, endDate);
        return repayService.findTrack(repayRequest, pageRequest);
    }

    private RepayRequest initRepayRequest(Long goodsId, Integer installmentNo, String startDate, String endDate) {
        Long orgId = session.get("orgId", Long.class);

        RepayRequest request = RepayRequest.builder()
                .orgId(orgId)
                .build();

        if (null != startDate) {
            request.setStartDate(LocalDate.parse(startDate, DateUtil.DEFAULT_DATE_FORMATTER));
        }

        if (null != endDate) {
            request.setEndDate(LocalDate.parse(endDate, DateUtil.DEFAULT_DATE_FORMATTER));
        }

        if (null != goodsId) {
            request.setGoodsId(goodsId);
        }

        if (null != installmentNo) {
            request.setInstallmentNo(installmentNo);
        }

        return request;
    }
}
