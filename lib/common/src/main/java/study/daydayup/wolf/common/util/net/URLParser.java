package study.daydayup.wolf.common.util.net;

import study.daydayup.wolf.common.model.type.net.InvalidURLException;
import study.daydayup.wolf.common.model.type.net.URL;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.common.util.net
 *
 * @author Wingle
 * @since 2020/5/11 6:41 下午
 **/
public class URLParser {
    private static final String PROTOCOL_DELIMITER = "://";
    private static final String QUERY_DELIMITER = "?";

    private URL url;
    private String str;
    public URL parse(String s) {
        url = new URL();
        str = s;
        if (StringUtil.isBlank(str)) {
            return url;
        }

        parseProtocol();
        parseHostAndPath();

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
        String sArr[] = StringUtil.split(str, QUERY_DELIMITER);

    }






}
