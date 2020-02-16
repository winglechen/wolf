package study.daydayup.wolf.framework.dts.transeformation;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.common.io.db.Table;

/**
 * study.daydayup.wolf.framework.dts.transeformer
 *
 * @author Wingle
 * @since 2020/2/16 6:54 下午
 **/
@Component
public class DbTransformation implements Transformation {
    public Statistics transform(Table data) {
        return null;
    }
}
