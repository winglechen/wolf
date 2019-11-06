package study.daydayup.wolf.model.type.number.base;

import lombok.Data;
import study.daydayup.wolf.model.contract.DataType;

/**
 * study.daydayup.wolf.model.type.number
 *
 * @author Wingle
 * @since 2019/10/16 9:42 上午
 **/
@Data
public class Long implements DataType {
    private int length = 11;

    private boolean unsigned = false;
    private boolean positive = false;
    private boolean negative = true;
    private long defaultValue = 0;
}
