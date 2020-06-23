package study.daydayup.wolf.business.org.biz.info.api;

import study.daydayup.wolf.business.org.api.info.entity.Company;
import study.daydayup.wolf.business.org.api.info.service.CompanyService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.info.api
 *
 * @author Wingle
 * @since 2020/6/23 5:05 下午
 **/
@RpcService
public class CompanyServiceImpl implements CompanyService {


    @Override
    public Result<List<Company>> findAll() {
        return null;
    }
}
