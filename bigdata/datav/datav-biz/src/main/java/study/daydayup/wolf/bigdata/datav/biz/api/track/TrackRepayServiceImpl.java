package study.daydayup.wolf.bigdata.datav.biz.api.track;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.bigdata.datav.api.dto.track.RepayRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.track.TrackRepay;
import study.daydayup.wolf.bigdata.datav.api.service.track.TrackRepayService;
import study.daydayup.wolf.bigdata.datav.biz.converter.track.TrackRepayConverter;
import study.daydayup.wolf.bigdata.datav.biz.dal.dao.TrackRepayDAO;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.TrackRepayDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.bigdata.datav.biz.api.track
 *
 * @author Wingle
 * @since 2020/3/26 6:07 下午
 **/
@RpcService(protocol = "dubbo")
public class TrackRepayServiceImpl implements TrackRepayService {
    @Resource
    private TrackRepayDAO repayDAO;

    @Override
    public Result<Page<TrackRepay>> findTrack(@Validated RepayRequest repayRequest, @NonNull PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<TrackRepayDO> repayDOList = repayDAO.selectByTrack(repayRequest);
        if (CollectionUtil.isEmpty(repayDOList)) {
            return Result.ok(
                    Page.empty(pageReq.getPageNum(), pageReq.getPageSize())
            );
        }

        List<TrackRepay> repayList = TrackRepayConverter.toModel(repayDOList);
        return Result.ok(Page.of(repayDOList).to(repayList));
    }
}
