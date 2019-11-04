package study.daydayup.wolf.demo.ali.provider;

import study.daydayup.wolf.demo.ali.api.exception.HelloBizException;
import study.daydayup.wolf.demo.ali.api.service.HelloService;
import study.daydayup.wolf.framework.rpc.RpcService;

/**
 * study.daydayup.wolf.demo.ali.provider
 *
 * @author Wingle
 * @since 2019/10/11 9:59 上午
 **/
@RpcService(protocol = "dubbo")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name + " from dubbo service: " + this.toString();
    }

    @Override
    public String sayException(String name) {
        throw new HelloBizException("Exception from the provider");
    }
}
