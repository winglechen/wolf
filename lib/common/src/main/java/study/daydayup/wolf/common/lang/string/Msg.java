package study.daydayup.wolf.common.lang.string;


import com.google.common.base.Joiner;

import javax.annotation.Nullable;

/**
 * study.daydayup.wolf.common.lang.string
 *
 * @author Wingle
 * @since 2019/12/12 3:34 下午
 **/
public class Msg {
    public static String join(String f, @Nullable Object s, Object... r) {
        Joiner joiner = Joiner.on(" ").skipNulls();
        return joiner.join(f, s, r);
    }
}
