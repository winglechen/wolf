package study.daydayup.wolf.bigdata.datav.api.service.track;

import study.daydayup.wolf.bigdata.datav.api.dto.track.RepayRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.track.TrackRepay;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.bigdata.datav.api.service.track
 *
 * @author Wingle
 * @since 2020/3/26 6:06 下午
 **/
public interface TrackRepayService extends Service {
    Result<Page<TrackRepay>> findTrack(RepayRequest repayRequest, PageRequest pageReq);
}
