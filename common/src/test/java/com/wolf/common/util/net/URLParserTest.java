package com.wolf.common.util.net;

import org.junit.Test;
import com.wolf.common.model.type.net.URL;

import static org.junit.Assert.*;

/**
 * com.wolf.common.util.net
 *
 * @author Wingle
 * @since 2020/5/11 6:46 下午
 **/
public class URLParserTest {

    @Test
    public void parse() {
        String s = "oss://onedot-private/img/a.jgp";
        URL url = URLParser.parse(s);

        assertEquals("URLParser parse fail", "oss", url.getProtocol());
        assertEquals("URLParser parse fail", "onedot-private", url.getHost());
        assertEquals("URLParser parse fail", "/img/a.jgp", url.getPath());
    }
}