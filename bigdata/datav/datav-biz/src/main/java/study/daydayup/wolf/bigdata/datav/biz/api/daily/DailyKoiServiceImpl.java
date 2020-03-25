package study.daydayup.wolf.bigdata.datav.biz.api.daily;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyKoi;
import study.daydayup.wolf.bigdata.datav.api.service.daily.DailyKoiService;
import study.daydayup.wolf.bigdata.datav.biz.dal.dao.DailyKoiDAO;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.bigdata.datav.biz.api.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:08 上午
 **/
@RpcService(protocol = "dubbo")
public class DailyKoiServiceImpl implements DailyKoiService {
    @Resource
    private DailyKoiDAO koiDAO;

    @Override
    public Page<DailyKoi> findByRange(@Validated DateRangeRequest request) {
        return null;
    }
}
