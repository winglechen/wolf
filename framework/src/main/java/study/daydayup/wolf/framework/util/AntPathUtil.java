package study.daydayup.wolf.framework.util;

import org.springframework.util.AntPathMatcher;

/**
 * study.daydayup.wolf.framework.util
 *
 * @author Wingle
 * @since 2020/2/26 12:14 下午
 **/
public class AntPathUtil {
    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    public static boolean match(String pattern, String path) {
        return MATCHER.match(pattern, path);
    }
}
