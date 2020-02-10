package study.daydayup.wolf.common.lang.string;

import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Nullable;

/**
 * study.daydayup.wolf.common.lang.string
 *
 * @author Wingle
 * @since 2019/12/12 3:34 下午
 **/
public class Str {
    public static String join(String f, @Nullable Object s, Object... r) {
        return StringUtil.joinWith(StringUtil.BLANK, f, s, r);
    }

}
