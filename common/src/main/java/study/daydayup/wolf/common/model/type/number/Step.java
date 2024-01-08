package study.daydayup.wolf.common.model.type.number;

import lombok.Data;

/**
 * study.daydayup.wolf.common.model.type.number
 *
 * @author Wingle
 * @since 2020/2/21 1:16 上午
 **/
@Data
public class Step {
    private int total;
    private int current;

    public static Step of(int current, int total) {
        return new Step(current, total);
    }

    public Step(int current, int total) {
        this.current = current;
        this.total = total;
    }
}
