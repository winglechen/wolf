package study.daydayup.wolf.framework.layer.dal.scanner;

import org.springframework.stereotype.Component;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/5 5:32 下午
 **/
@Component
public class Offset implements OffsetHolder {
    @Override
    public Long get(String table, String shard) {
        return null;
    }

    @Override
    public void set(String table, String shard, Long id) {

    }
}
