package study.daydayup.wolf.framework.security.sensitive;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SensitiveHttpHeaderFilterTest {
    @Test
    public void test_filter() {
        Map<String, String> headers = new HashMap<>();

        headers.put("X-IBM-Client-Secret", "v1");
        headers.put("Authorization", "v1");

        Map<String, String> newHeaders = SensitiveHttpHeaderFilter.filter(headers);

        assertTrue(headers.containsKey("Authorization"));
        assertTrue(headers.containsKey("X-IBM-Client-Secret"));

        assertFalse(newHeaders.containsKey("Authorization"));
        assertFalse(newHeaders.containsKey("X-IBM-Client-Secret"));
    }

    @Test
    public void test_erase() {
        Map<String, String> headers = new HashMap<>();

        headers.put("X-IBM-Client-Secret", "v1");
        headers.put("Authorization", "v1");

        Map<String, String> newHeaders = SensitiveHttpHeaderFilter.erase(headers);

        assertTrue(headers.containsKey("Authorization"));
        assertTrue(headers.containsKey("X-IBM-Client-Secret"));

        assertTrue(newHeaders.containsKey("Authorization"));
        assertTrue(newHeaders.containsKey("X-IBM-Client-Secret"));

        assertEquals("-", newHeaders.get("X-IBM-Client-Secret"));
        assertEquals("-", newHeaders.get("Authorization"));
    }
}