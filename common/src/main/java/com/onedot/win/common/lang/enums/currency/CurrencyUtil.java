package com.onedot.win.common.lang.enums.currency;

import com.onedot.win.common.lang.enums.CodeBasedEnum;
import com.onedot.win.common.util.lang.EnumUtil;
import com.onedot.win.common.util.lang.StringUtil;

/**
 * com.onedot.win.common.lang.enums.currency
 *
 * @author Wingle
 * @since 2020/9/1 4:18 下午
 **/
public class CurrencyUtil {
    public static CurrencyEnum parse(String name) {
        return parse(name, null);
    }

    public static CurrencyEnum parse(String name, CurrencyEnum defaultCurrency) {
        if (StringUtil.isBlank(name)) {
            return defaultCurrency;
        }

        return EnumUtil.nameOf(name, CurrencyEnum.class, true);
    }

    public static CodeBasedEnum codeOf(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumUtil.codeOf(code, CurrencyEnum.class);
    }

    public static String getCurrencyCode(Integer code) {
        if (code == null) {
            return "";
        }

        CodeBasedEnum currency = EnumUtil.codeOf(code, CurrencyEnum.class);
        return currency.getName();
    }

    public static String getCurrencyCode(CurrencyEnum currency) {
        if (currency == null) {
            return "";
        }

        return currency.getName();
    }
}
