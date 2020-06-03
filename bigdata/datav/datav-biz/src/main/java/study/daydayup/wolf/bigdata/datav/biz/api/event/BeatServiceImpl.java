package study.daydayup.wolf.bigdata.datav.biz.api.event;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.bigdata.datav.api.entity.event.Beat;
import study.daydayup.wolf.bigdata.datav.api.service.event.BeatService;
import study.daydayup.wolf.bigdata.datav.biz.converter.event.BeatConverter;
import study.daydayup.wolf.bigdata.datav.biz.dal.dao.BeatDAO;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.BeatDO;
import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.common.util.net.URLUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;

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
            return Result.fail(10000, "invalid event");
        }

        int status = beatDAO.insertSelective(beatDO);
        return Result.ok(status);
    }

    @Override
    public Result<Integer> add(Long accountId, Long orgId, String query) {
        Beat beat = parseQuery(accountId, orgId, query);
        if (beat == null) {
            return Result.fail(10000, "invalid event");
        }

        if (null == beat.getEventType()) {
            return Result.fail(10000, "eventType can't be null");
        }

        return add(beat);
    }

    private Beat parseQuery(Long accountId, Long orgId, String query) {
        if (StringUtil.isBlank(query)) {
             return null;
        }
        Map<String, String> data = URLUtil.parseQuery(query);
        if (MapUtil.isEmpty(data)) {
            return null;
        }

        Beat beat = Beat.builder()
                .accountId(accountId)
                .orgId(orgId)
                .deviceId(data.get("deviceId"))
                .deviceType(data.get("deviceType"))
                .ip(data.get("ip"))
                .eventType(data.get("eventType"))
                .event(data.get("event"))
                .eventContext(data.get("context"))
                .eventMemo(data.get("memo"))
                .build();

        if (null == beat.getOrgId() && null != data.get("orgId")) {
            beat.setOrgId(Long.valueOf(data.get("orgId")));
        }

        if (null != data.get("longitude")) {
            beat.setLongitude(new BigDecimal(data.get("longitude")));
        }

        if (null != data.get("latitude")) {
            beat.setLatitude(new BigDecimal(data.get("latitude")));
        }

        return beat;
    }


}
