package study.daydayup.wolf.business.union.app.controller.data;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.bigdata.datav.api.entity.event.Beat;
import study.daydayup.wolf.bigdata.datav.api.service.event.BeatService;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.app.controller.dev
 *
 * @author Wingle
 * @since 2020/6/2 11:07 上午
 **/
@RestController
public class BeatController {
    @Reference
    private BeatService beatService;

    @Resource
    private Session session;

    @PostMapping("/log/beat/add")
    public Result<Integer> add(@Validated @RequestBody Beat beat) {
        Long accountId = session.get("accountId", Long.class, false);
        Long orgId = session.get("orgId", Long.class, false);

        if (accountId != null) {
            beat.setAccountId(accountId);
        }
        if (orgId != null) {
            beat.setOrgId(orgId);
        }

        return beatService.add(beat);
    }
}
