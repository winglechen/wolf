package com.onedot.win.framework.security.sensitive;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IndiaSensitiveInformationTest {

    @Test
    public void mask_null() {
        String phone = null;
        String expect = null;

        String result = IndiaSensitiveInformation.mask(phone, 5);
        assertEquals("IndiaSensitiveInformation.mask failed", result, expect);
    }

    @Test
    public void mask_blank_1() {
        String phone = "";
        String expect = "";

        String result = IndiaSensitiveInformation.mask(phone, 5);
        assertEquals("IndiaSensitiveInformation.mask failed", result, expect);
    }

    @Test
    public void mask_blank_2() {
        String phone = "  ";
        String expect = "  ";

        String result = IndiaSensitiveInformation.mask(phone, 5);
        assertEquals("IndiaSensitiveInformation.mask failed", result, expect);
    }

    @Test
    public void mask_5() {
        String phone = "1234567890";
        String expect = "12345XXXXX";

        String result = IndiaSensitiveInformation.mask(phone, 5);
        assertEquals("IndiaSensitiveInformation.mask failed", result, expect);
    }

    @Test
    public void mask_4() {
        String phone = "1234567890";
        String expect = "123456XXXX";

        String result = IndiaSensitiveInformation.mask(phone, 4);
        assertEquals("IndiaSensitiveInformation.mask failed", result, expect);
    }

    @Test
    public void mask_3() {
        String phone = "123";
        String expect = "XXX";

        String result = IndiaSensitiveInformation.mask(phone, 4);
        assertEquals("IndiaSensitiveInformation.mask failed", result, expect);
    }

    @Test
    public void test_mock_phone() {
        String phone = "1234567890";
        String expect = "12345XXXXX";

        String result = IndiaSensitiveInformation.maskPhone(phone);
        assertEquals("IndiaSensitiveInformation.maskPhone failed", result, expect);
    }

    @Test
    public void maskPhone() {
        String phone = "123456";
        String expect = "12345X";

        String result = IndiaSensitiveInformation.maskPhone(phone);
        assertEquals("IndiaSensitiveInformation.maskPhone failed", result, expect);
    }

    @Test
    public void maskCardNo() {
    }

    @Test
    public void email() {
        String phone = "123456@gmail.com";
        String expect = "12XXXX@gmail.com";

        String result = IndiaSensitiveInformation.maskEmail(phone);
        assertEquals("IndiaSensitiveInformation.maskEmail failed", result, expect);
    }

    @Test
    public void vpa() {
        String phone = "123456@ybl";
        String expect = "12XXXX@ybl";

        String result = IndiaSensitiveInformation.maskEmail(phone);
        assertEquals("IndiaSensitiveInformation.vpa failed", result, expect);
    }
}