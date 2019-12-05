package study.daydayup.wolf.business.account.biz.dal.dao;

import study.daydayup.wolf.business.account.biz.dal.dataobject.AlipayAccountDO;

public interface AlipayAccountDAO {
    int deleteById(Long id);

    int insert(AlipayAccountDO record);

    int insertSelective(AlipayAccountDO record);

    AlipayAccountDO selectById(Long id);

    int updateByIdSelective(AlipayAccountDO record);

    int updateById(AlipayAccountDO record);
}