package study.daydayup.wolf.bigdata.datav.biz.api.track;

import study.daydayup.wolf.bigdata.datav.api.dto.track.RepayRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.track.TrackRepay;
import study.daydayup.wolf.bigdata.datav.api.service.track.TrackRepayService;
import study.daydayup.wolf.bigdata.datav.biz.dal.dao.TrackRepayDAO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;

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
    public Result<Page<TrackRepay>> findTrack(RepayRequest repayRequest, PageRequest pageReq) {
        return null;
    }
}
