package study.daydayup.wolf.dts.transformation.matcher;

import lombok.NonNull;

/**
 * study.daydayup.wolf.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 5:56 下午
 **/
public abstract class AbstractMatcher implements Matcher {
    protected String column;
    protected Object value;

    @Override
    public void init(@NonNull String column, Object value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public void init(@NonNull String column) {
         this.column = column;
    }

}
