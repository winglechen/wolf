package com.wolf.common.util.net;

import com.wolf.common.model.type.net.InvalidURLException;
import com.wolf.common.model.type.net.URL;
import com.wolf.common.util.lang.StringUtil;

/**
 * com.wolf.common.util.net
 *
 * @author Wingle
 * @since 2020/5/11 6:41 下午
 **/
public class URLParser {
    private static final String PROTOCOL_DELIMITER = "://";
    private static final String QUERY_DELIMITER = "?";

    private URL url;
    private String str;
    public static URL parse(String s) {
        return new URLParser().parseURL(s);
    }

    public URL parseURL(String s) {
        url = new URL();
        str = s;
        if (StringUtil.isBlank(str)) {
            return url;
        }

        parseProtocol();
        parseHostAndPath();
        parseQuery();

        return url;
    }

    private void parseProtocol() {
        String sArr[] = StringUtil.split(str, PROTOCOL_DELIMITER);
        if (2 != sArr.length) {
            throw new InvalidURLException("protocol not found");
        }

        str = sArr[1];
        url.setProtocol(sArr[0]);
    }

    private void parseHostAndPath() {
        if (StringUtil.isBlank(str)) {
            throw new InvalidURLException("host not found");
        }

        String sArr[] = StringUtil.split(str, QUERY_DELIMITER);
        if (sArr.length > 2) {
            throw new InvalidURLException("too many query delimiter");
        }

        str = (2 == sArr.length) ? sArr[1] : null;
        splitHostAndPath(sArr[0]);
    }

    private void splitHostAndPath(String s) {
        if (StringUtil.isBlank(s)) {
            return;
        }

        String sArr[] = s.split("/", 2);
        url.setHost(sArr[0]);

        if (2 == sArr.length) {
            url.setPath(StringUtil.join("/", sArr[1]));
        }
    }

    private void parseQuery() {

    }






}
