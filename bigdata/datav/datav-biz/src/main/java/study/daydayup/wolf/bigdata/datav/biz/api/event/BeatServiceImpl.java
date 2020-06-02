package study.daydayup.wolf.bigdata.datav.biz.api.event;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.bigdata.datav.api.entity.event.Beat;
import study.daydayup.wolf.bigdata.datav.api.service.event.BeatService;
import study.daydayup.wolf.bigdata.datav.biz.converter.event.BeatConverter;
import study.daydayup.wolf.bigdata.datav.biz.dal.dao.BeatDAO;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.BeatDO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.bigdata.datav.biz.api.event
 *
 * @author Wingle
 * @since 2020/6/2 11:00 上午
 **/
@RpcService
public class BeatServiceImpl implements BeatService {
    @Resource
    private BeatDAO beatDAO;
    @Override
    public Result<Integer> add(@Validated Beat beat) {
        BeatDO beatDO = BeatConverter.toDO(beat);
        if (beatDO == null) {
            return Result.fail(10500, "invalid event");
        }

        int status = beatDAO.insertSelective(beatDO);
        return Result.ok(status);
    }
}
