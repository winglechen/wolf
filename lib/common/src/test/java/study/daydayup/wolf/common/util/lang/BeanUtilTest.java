package study.daydayup.wolf.common.util.lang;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util.lang
 *
 * @author Wingle
 * @since 2020/2/14 5:47 下午
 **/
public class BeanUtilTest {

    @Test
    public void testEquals() {
        assertTrue("ObjectUtil.equals fail", BeanUtil.equals(Integer.valueOf(1), 1));
        assertTrue("ObjectUtil.equals fail", BeanUtil.equals(Long.valueOf(1), 1L));
        assertTrue("ObjectUtil.equals fail", BeanUtil.equals(Double.valueOf(1.0), 1.0));
    }
}