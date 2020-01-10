package study.daydayup.wolf.business.trade.tm.biz.engine;

/**
 * study.daydayup.wolf.business.trade.tm.biz.engine
 *
 * @author Wingle
 * @since 2020/1/10 3:07 下午
 **/
public interface FilterRegistry {
    void init(QueryEngine engine);
    void register();
}
