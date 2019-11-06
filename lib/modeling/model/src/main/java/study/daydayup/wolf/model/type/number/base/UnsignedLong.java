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
public class UnsignedLong implements DataType {
    private int length = 20;

    private boolean unsigned = true;
    private long defaultValue = 0;
}
