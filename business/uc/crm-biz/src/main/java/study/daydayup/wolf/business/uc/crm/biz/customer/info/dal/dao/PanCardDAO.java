package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.PanCardDO;

public interface PanCardDAO {
    int deleteById(Long id);

    int insert(PanCardDO record);

    int insertSelective(PanCardDO record);

    PanCardDO selectById(Long id);

    int updateByIdSelective(PanCardDO record);

    int updateById(PanCardDO record);
}