package study.daydayup.wolf.sdk.domain.oss;

/**
 * study.daydayup.wolf.sdk.domain
 *
 * @author Wingle
 * @since 2020/5/11 2:07 下午
 **/
public interface OSS {
    String decode(String str);
    String encode(String path, String namespace);
}
