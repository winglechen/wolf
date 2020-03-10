package study.daydayup.wolf.business.uc.crm.biz.customer.credit.api;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.business.uc.api.crm.customer.service.CreditLogService;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter.CreditLogConverter;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditLogDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLogDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.api
 *
 * @author Wingle
 * @since 2020/3/10 1:20 下午
 **/
@RpcService(protocol = "dubbo")
public class CreditLogServiceImpl implements CreditLogService {
    @Resource
    private CreditLogDAO dao;

    @Override
    public Page<CreditLog> find(Long accountId, Long orgId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());

        List<CreditLogDO> logDOList = dao.selectByOrgIdAndAccountId(orgId, accountId);
        if (CollectionUtil.isEmpty(logDOList)) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        List<CreditLog> logList = CreditLogConverter.toModel(logDOList);
        return Page.of(logDOList).to(logList);
    }

    @Override
    public Page<CreditLog> findByAccount(Long accountId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());

        List<CreditLogDO> logDOList = dao.selectByAccountId(accountId);
        if (CollectionUtil.isEmpty(logDOList)) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        List<CreditLog> logList = CreditLogConverter.toModel(logDOList);
        return Page.of(logDOList).to(logList);
    }

    @Override
    public Page<CreditLog> findByOrg(Long orgId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());

        List<CreditLogDO> logDOList = dao.selectByAccountId(orgId);
        if (CollectionUtil.isEmpty(logDOList)) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        List<CreditLog> logList = CreditLogConverter.toModel(logDOList);
        return Page.of(logDOList).to(logList);
    }
}
