package study.daydayup.wolf.common.io.db.matcher;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:09 下午
 **/
public class MatcherGateway extends AbstractMatcher implements Matcher {
    private List<Matcher> matcherList;

    public MatcherGateway(){
        matcherList = new ArrayList<>();
    }

    @Override
    public boolean match(Row row) {
        if (matcherList.isEmpty()) {
            return true;
        }

        for (Matcher matcher : matcherList) {
            if (!matcher.match(row)) {
                return false;
            }
        }

        return true;
    }

    public MatcherGateway isNull(String column) {
        Matcher matcher = new NullMatcher();

        matcher.init(column);
        matcherList.add(matcher);

        return this;
    }

    public MatcherGateway notNull(String column) {
        Matcher matcher = new NotNullMatcher();

        matcher.init(column);
        matcherList.add(matcher);

        return this;
    }

    public MatcherGateway equal(String column, Object value) {
        Matcher matcher = new EqualMatcher();

        initMatcher(matcher, column, value);

        return this;
    }

    public MatcherGateway notEqual(String column, Object value) {
        Matcher matcher = new NotEqualMatcher();

        initMatcher(matcher, column, value);

        return this;
    }

    public MatcherGateway greaterThan(String column, Object value) {
        Matcher matcher = new GreaterMatcher();

        initMatcher(matcher, column, value);

        return this;
    }

    public MatcherGateway greaterOrEqual(String column, Object value) {
        Matcher matcher = new GreaterOrEqualMatcher();

        initMatcher(matcher, column, value);

        return this;
    }

    public MatcherGateway lessThan(String column, Object value) {
        Matcher matcher = new LessMatcher();

        initMatcher(matcher, column, value);

        return this;
    }

    public MatcherGateway lessOrEqual(String column, Object value) {
        Matcher matcher = new LessOrEqualMatcher();

        initMatcher(matcher, column, value);

        return this;
    }

    public MatcherGateway between(String column, Object start, Object end) {
        BetweenMatcher matcher = new BetweenMatcher();

        matcher.init(column, start, end);
        matcherList.add(matcher);

        return this;
    }

    public MatcherGateway notBetween(String column, Object start, Object end) {
        NotBetweenMatcher matcher = new NotBetweenMatcher();

        matcher.init(column, start, end);
        matcherList.add(matcher);

        return this;
    }

    public MatcherGateway in(@NonNull String column, @NonNull Collection<Object> values) {
        InMatcher matcher = new InMatcher();

        matcher.init(column, values);
        matcherList.add(matcher);

        return this;
    }

    public MatcherGateway hasTag(@NonNull String tags) {
        return hasTag(Table.DEFAULT_TAG_COLUMN, tags);
    }

    public MatcherGateway hasTag(@NonNull String column, @NonNull String tag) {
        TagMatcher matcher = new TagMatcher();

        matcher.init(column, tag);
        matcherList.add(matcher);

        return this;
    }

    private void initMatcher(@NonNull Matcher matcher, @NonNull String column, @NonNull Object value) {
        matcher.init(column, value);
        matcherList.add(matcher);
    }

}
