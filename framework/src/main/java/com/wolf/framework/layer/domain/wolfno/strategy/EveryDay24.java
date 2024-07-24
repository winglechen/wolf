package com.wolf.framework.layer.domain.wolfno.strategy;

import com.wolf.framework.layer.domain.wolfno.factory.AbstractWolfNoCreator;
import com.wolf.framework.layer.domain.wolfno.factory.WolfNoCreator;
import java.time.format.DateTimeFormatter;

public class EveryDay24 extends AbstractWolfNoCreator implements WolfNoCreator {
    private static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("MMdd");

    @Override
    public String create() {
        StringBuilder sb = new StringBuilder();

        int year = getCreateTime().getYear() % 1000;
        sb.append(year);
        sb.append(DAY_FORMATTER.format(getCreateTime()));
        sb.append(String.format("%03d", context.getType()));
        sb.append(context.getDatacenter());
        sb.append(context.getShard());
        sb.append(getWolfID());

        return sb.toString();
    }
}
