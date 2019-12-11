package study.daydayup.wolf.business.goods.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsInstallmentDO;

@Mapper
public interface GoodsInstallmentDAO {
    int insert(GoodsInstallmentDO record);

    int insertSelective(GoodsInstallmentDO record);

    GoodsInstallmentDO selectById(Long id);

    int updateByIdSelective(GoodsInstallmentDO record);

    int updateById(GoodsInstallmentDO record);
}