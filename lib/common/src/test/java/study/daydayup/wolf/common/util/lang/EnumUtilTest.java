package study.daydayup.wolf.common.util.lang;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.GenderEnum;
import study.daydayup.wolf.common.lang.exception.enums.UnsupportedEnumCodeException;

import static org.junit.Assert.assertTrue;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/9/30 11:18 AM
 **/
public class EnumUtilTest {

    @Test
    public void test_code_of_work_fine() {
        GenderEnum male = GenderEnum.MALE;
        GenderEnum unknown = EnumUtil.codeOf(1, GenderEnum.class);

        assertTrue("EnumUtil.codeOf() get code fail", male.equals(unknown));
    }


    @Test(expected = UnsupportedEnumCodeException.class)
    public void test_code_of_throws_exception_work() {
        GenderEnum unknow = EnumUtil.codeOf(11, GenderEnum.class);
    }
}
