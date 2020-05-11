package study.daydayup.wolf.sdk.domain.oss;

import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.sdk.domain
 *
 * @author Wingle
 * @since 2020/5/11 2:10 下午
 **/
public abstract class AbstractOSS implements OSS {
    @Override
    public String encode(String path, String namespace) {
        return StringUtil.join(namespace, path);
    }
}
