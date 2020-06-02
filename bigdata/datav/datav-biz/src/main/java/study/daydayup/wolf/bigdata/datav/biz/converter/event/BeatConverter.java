package study.daydayup.wolf.bigdata.datav.biz.converter.event;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.bigdata.datav.api.entity.event.Beat;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.BeatDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.bigdata.datav.biz.converter.event
 *
 * @author Wingle
 * @since 2020/3/26 12:24 上午
 **/
public class BeatConverter implements Converter {
    public static BeatDO toDO(Beat beat) {
        if (beat == null) {
            return null;
        }

        BeatDO beatDO = new BeatDO();
        BeanUtils.copyProperties(beat, beatDO);
        return beatDO;
    }

    public static Beat toModel(BeatDO beatDO) {
        if (beatDO == null) {
            return null;
        }

        Beat beat = new Beat();
        BeanUtils.copyProperties(beatDO, beat);

        return beat;
    }

    public static List<Beat> toModel(List<BeatDO> beatDOList) {
        return CollectionUtil.to(beatDOList, BeatConverter::toModel);
    }

}
