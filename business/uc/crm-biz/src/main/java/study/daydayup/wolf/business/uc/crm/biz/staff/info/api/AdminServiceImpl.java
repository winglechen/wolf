package study.daydayup.wolf.business.uc.crm.biz.staff.info.api;

import study.daydayup.wolf.business.uc.api.crm.staff.entity.info.Admin;
import study.daydayup.wolf.business.uc.api.crm.staff.service.info.AdminService;
import study.daydayup.wolf.business.uc.crm.biz.staff.info.converter.AdminConverter;
import study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dao.AdminDAO;
import study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dataobject.AdminDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.staff.info.api
 *
 * @author Wingle
 * @since 2020/4/22 10:01 下午
 **/
@RpcService(protocol = "dubbo")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDAO dao;

    @Override
    public Result<Page<Admin>> findByStaff(Long accountId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());

        List<AdminDO> adminDOList = dao.selectByAccountId(accountId);
        if (CollectionUtil.isEmpty(adminDOList)) {
            return Result.ok(Page.empty(pageReq.getPageNum(), pageReq.getPageSize()));
        }

        List<Admin> adminList = AdminConverter.toModel(adminDOList);
        return Result.ok(
                Page.of(adminDOList).to(adminList)
        );
    }
}
