package study.daydayup.wolf.bigdata.datav.api.service.event;

import study.daydayup.wolf.bigdata.datav.api.entity.event.Beat;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.bigdata.datav.api.service.event
 *
 * @author Wingle
 * @since 2020/6/2 10:58 上午
 **/
public interface BeatService {
    Result<Integer> add(Beat beat);
    Result<Integer> add(Long accountId, Long orgId, String query);
}
