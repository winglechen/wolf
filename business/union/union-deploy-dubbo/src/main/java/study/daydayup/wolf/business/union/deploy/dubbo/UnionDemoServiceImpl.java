package study.daydayup.wolf.business.union.deploy.dubbo;

import study.daydayup.wolf.business.union.api.UnionDemoService;
import study.daydayup.wolf.framework.rpc.RpcService;

/**
 * study.daydayup.wolf.business.union.deploy.dubbo
 *
 * @author Wingle
 * @since 2019/12/10 7:15 下午
 **/
@RpcService(protocol = "dubbo")
public class UnionDemoServiceImpl implements UnionDemoService {
    @Override
    public String show() {
        return "I am UnionDemoService from the union-deploy-dubbo";
    }
}
