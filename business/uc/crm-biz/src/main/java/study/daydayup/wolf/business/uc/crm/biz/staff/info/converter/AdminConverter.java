package study.daydayup.wolf.business.uc.crm.biz.staff.info.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.staff.entity.info.Admin;
import study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dataobject.AdminDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class AdminConverter implements Converter {
    public static AdminDO toDO(Admin admin) {
        if (admin == null) {
            return null;
        }

        AdminDO adminDO = new AdminDO();
        BeanUtils.copyProperties(admin, adminDO);
        return adminDO;
    }

    public static Admin toModel(AdminDO adminDO) {
        if (adminDO == null) {
            return null;
        }

        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDO, admin);

        return admin;
    }

    public static List<Admin> toModel(List<AdminDO> adminDOList) {
        return CollectionUtil.to(adminDOList, AdminConverter::toModel);
    }

}
