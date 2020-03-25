package study.daydayup.wolf.bigdata.datav.biz.converter.daily;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyKoi;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyKoiDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.bigdata.datav.biz.converter.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:24 上午
 **/
public class DailyKoiConverter implements Converter {
    public static DailyKoiDO toDO(DailyKoi koi) {
        if (koi == null) {
            return null;
        }

        DailyKoiDO koiDO = new DailyKoiDO();
        BeanUtils.copyProperties(koi, koiDO);
        return koiDO;
    }

    public static DailyKoi toModel(DailyKoiDO koiDO) {
        if (koiDO == null) {
            return null;
        }

        DailyKoi koi = new DailyKoi();
        BeanUtils.copyProperties(koiDO, koi);

        return koi;
    }

    public static List<DailyKoi> toModel(List<DailyKoiDO> koiDOList) {
        return CollectionUtil.to(koiDOList, DailyKoiConverter::toModel);
    }

}
