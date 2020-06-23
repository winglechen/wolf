package study.daydayup.wolf.business.org.api.info.service;

import study.daydayup.wolf.business.org.api.info.entity.Company;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

import java.util.List;

/**
 * study.daydayup.wolf.business.org.api.info.service
 *
 * @author Wingle
 * @since 2020/6/23 5:04 下午
 **/
public interface CompanyService extends Service {
    Result<List<Company>> findAll();
}
