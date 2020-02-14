package study.daydayup.wolf.common.util;

import org.junit.Test;
import study.daydayup.wolf.common.util.lang.StringUtil;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/12/10 9:57 上午
 **/
public class StringUtilTest {

    @Test
    public void hasValue() {
        assertTrue("space hasValue", StringUtil.hasValue(" "));
        assertFalse("null do not hasValue", StringUtil.hasValue(null));
        assertFalse("empty string do not hasValue", StringUtil.hasValue(""));
        assertTrue("common string hasValue", StringUtil.hasValue("abc"));
    }

    @Test
    public void isTrue() {
    }

    @Test
    public void isFalse() {
    }

    @Test
    public void containsSpace() {
    }

    @Test
    public void ltrim() {
        String a = "abcdef";
        String prefix = "abc";

        a = StringUtil.ltrim(a, prefix);
        String expected = "def";

        assertEquals("StringUtil.ltrim fail", expected, a);
    }

    @Test
    public void rtrim() {
        String a = "abcdef";
        String suffix = "def";
        String expected = "abc";

        a = StringUtil.rtrim(a, suffix);
        assertEquals("StringUtil.rtrim fail", expected, a);

    }

}