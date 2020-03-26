package study.daydayup.wolf.bigdata.datav.biz.converter.track;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.bigdata.datav.api.entity.track.TrackRepay;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.TrackRepayDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.bigdata.datav.biz.converter.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:24 上午
 **/
public class TrackRepayConverter implements Converter {
    public static TrackRepayDO toDO(TrackRepay repay) {
        if (repay == null) {
            return null;
        }

        TrackRepayDO repayDO = new TrackRepayDO();
        BeanUtils.copyProperties(repay, repayDO);
        return repayDO;
    }

    public static TrackRepay toModel(TrackRepayDO repayDO) {
        if (repayDO == null) {
            return null;
        }

        TrackRepay repay = new TrackRepay();
        BeanUtils.copyProperties(repayDO, repay);

        return repay;
    }

    public static List<TrackRepay> toModel(List<TrackRepayDO> repayDOList) {
        return CollectionUtil.to(repayDOList, TrackRepayConverter::toModel);
    }

}
