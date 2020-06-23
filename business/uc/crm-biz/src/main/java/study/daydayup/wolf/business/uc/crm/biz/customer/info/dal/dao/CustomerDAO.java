package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.CustomerDO;

public interface CustomerDAO {
    int deleteById(Long id);

    int insert(CustomerDO record);

    int insertSelective(CustomerDO record);

    CustomerDO selectById(Long id);

    int updateByIdSelective(CustomerDO record);

    int updateById(CustomerDO record);
}