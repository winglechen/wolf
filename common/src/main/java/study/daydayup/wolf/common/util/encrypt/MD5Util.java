package study.daydayup.wolf.common.util.encrypt;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import lombok.NonNull;

/**
 * study.daydayup.wolf.common.util.encrypt
 *
 * @author Wingle
 * @since 2020/3/16 8:28 下午
 **/
public class MD5Util {
    @SuppressWarnings("ALL")
    public static String md5(@NonNull String str) {
        return Hashing.md5().newHasher().putString(str, Charsets.UTF_8).hash().toString();
    }

}
