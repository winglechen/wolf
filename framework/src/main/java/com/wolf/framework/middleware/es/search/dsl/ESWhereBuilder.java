package com.wolf.framework.middleware.es.search.dsl;

import lombok.NonNull;
import com.wolf.common.lang.ds.ObjectMap;
import com.wolf.common.lang.exception.lang.IllegalArgumentException;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.common.util.time.DateUtil;
import com.wolf.framework.middleware.es.enums.ESOperatorEnum;
import com.wolf.framework.middleware.es.search.AbstractSearchBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ESWhereBuilder extends AbstractSearchBuilder {
    private List<ObjectMap> queryList;

    public ESWhereBuilder() {
        queryList = new ArrayList<>();
    }

    public ESWhereBuilder where(@NonNull String column, @NonNull String operator, Object value, boolean filterNullClause, boolean filterBlankClause) {
        if (filterNullClause && value == null) {
            return this;
        }
        if (filterBlankClause) {
            if (value instanceof String && StringUtil.isBlank(value)) {
                return this;
            }
        }

        return where(column, operator, value);
    }

    public ESWhereBuilder where(@NonNull String column, @NonNull String operator, Object value) {
        Object parsedValue = parseValue(value);

        switch (operator) {
            case "=":
                return addTerm(column, parsedValue);
            case "in":
                return addTerms(column, parsedValue);
            case ">":
                return addRange(column, ESOperatorEnum.GREATER_THAN.getName(), parsedValue);
            case ">=":
                return addRange(column, ESOperatorEnum.GREATER_THAN_OR_EQUAL_TO.getName(), parsedValue);
            case "<":
                return addRange(column, ESOperatorEnum.LESS_THAN.getName(), parsedValue);
            case "<=":
                return addRange(column, ESOperatorEnum.LESS_THAN_OR_EQUAL_TO.getName(), parsedValue);
            default:
                throw new IllegalArgumentException("unknown operator " + operator);
        }
    }


    public ObjectMap toDsl() {
        if (queryList.isEmpty()) {
            return null;
        }

        ObjectMap query = new ObjectMap();
        ObjectMap bool = new ObjectMap();

        query.put("bool", bool);
        bool.put("must", queryList);

        return query;
    }

    private Object parseValue(Object value) {
        if (value instanceof LocalDateTime) {
            return DateUtil.asString((LocalDateTime) value);
        }
        if (value instanceof LocalDate) {
            return DateUtil.asString((LocalDate) value);
        }
        return value;
    }

    private ESWhereBuilder addTerm(String column, Object value) {
        ObjectMap term = new ObjectMap();
        ObjectMap field = new ObjectMap();

        field.put(column, value);
        term.put("term", field);

        queryList.add(term);

        return this;

    }


    private ESWhereBuilder addTerms(String column, Object value) {
        ObjectMap terms = new ObjectMap();
        ObjectMap field = new ObjectMap();

        field.put(column, value);
        terms.put("terms", field);

        queryList.add(terms);

        return this;

    }

    private ESWhereBuilder addRange(String column, String operator, Object value) {
        ObjectMap range = new ObjectMap();
        ObjectMap field = new ObjectMap();
        ObjectMap condition = new ObjectMap();

        condition.put(operator, value);
        field.put(column, condition);
        range.put("range", field);

        queryList.add(range);

        return this;
    }
}