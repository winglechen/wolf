package com.onedot.win.common.io.sql.clause;

import com.onedot.win.common.lang.contract.worker.Locator;
import com.onedot.win.common.util.lang.StringUtil;

/**
 * com.onedot.win.common.io.sql.clause
 *
 * @author Wingle
 * @since 2021/11/7 下午10:31
 **/
public class ClauseLocator implements Locator<String, Clause> {
    @Override
    public Clause locate(String s) {
        if (StringUtil.isBlank(s)) {
            return new DefaultClause();
        }

        switch (s.trim().toLowerCase()) {
            case "not in":
            case "in":
            case "not exists":
            case "exists":
                return new InClause();
            default:
                return new DefaultClause();
        }
    }
}
