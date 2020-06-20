package study.daydayup.wolf.bigdata.datav.biz.converter.daily;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyAudit;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailySms;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailySmsDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.bigdata.datav.biz.converter.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:24 上午
 **/
public class DailySmsConverter implements Converter {
    public static DailySmsDO toDO(DailySms sms) {
        if (sms == null) {
            return null;
        }

        DailySmsDO smsDO = new DailySmsDO();
        BeanUtils.copyProperties(sms, smsDO);
        return smsDO;
    }

    public static DailySms toModel(DailySmsDO smsDO) {
        if (smsDO == null) {
            return null;
        }

        DailySms sms = new DailySms();
        BeanUtils.copyProperties(smsDO, sms);

        return sms;
    }

    public static List<DailySms> toModel(List<DailySmsDO> smsDOList) {
        return CollectionUtil.to(smsDOList, DailySmsConverter::toModel);
    }
   
}
