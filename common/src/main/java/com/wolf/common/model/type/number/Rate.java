package com.wolf.common.model.type.number;

import com.wolf.common.lang.enums.unit.RateEnum;
import com.wolf.common.model.contract.DataType;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * com.wolf.model.type.number
 *
 * @author Wingle
 * @since 2019/12/19 3:25 下午
 **/
public class Rate implements DataType {
    public static final int TEN             = 10;
    public static final int HUNDRED         = 100;
    public static final int THOUSAND        = 1000;
    public static final int TEN_THOUSAND    = 10000;
    public static final int HUNDRED_THOUSAND= 100000;
    public static final int MILLION         = 1000000;

    public static final BigDecimal HUNDRED_PERCENT = BigDecimal.valueOf(100);


    private Decimal value;
    private RateEnum unit;

    public Rate(int value, RateEnum unit) {
        this.value = Decimal.of(value);
        this.unit = unit;
    }

    public Rate(BigDecimal value, RateEnum unit) {
        this.value = Decimal.of(value);
        this.unit = unit;
    }

    public Rate(BigDecimal value, int scale, RateEnum unit) {
        this.value = Decimal.of(value, scale);
        this.unit = unit;
    }

    public Decimal toHundred() {
        return convertTo(RateEnum.PER_HUNDRED);
    }

    public Decimal toThousand() {
        return convertTo(RateEnum.PER_THOUSAND);
    }

    public Decimal toTenThousand() {
        return convertTo(RateEnum.PRE_TEN_THOUSAND);
    }

    public Decimal toHundredThousand() {
        return convertTo(RateEnum.PRE_HUNDRED_THOUSAND);
    }

    public Decimal toMillion() {
        return convertTo(RateEnum.PER_MILLION);
    }

    public BigDecimal toBigDecimal() {
        return value.toBigDecimal().divide(unit.getBase(), MathContext.DECIMAL32);
    }

    private Decimal convertTo(RateEnum targetUnit) {
        if (unit.equals(targetUnit)) {
            return value;
        }

        int sourceCode = this.unit.getCode();
        int targetCode = targetUnit.getCode();
        int step = targetCode - sourceCode;

        BigDecimal newValue = BigDecimal.TEN;
        newValue = newValue.pow(step, MathContext.DECIMAL32);
        newValue = value.toBigDecimal().multiply(newValue);

        return Decimal.of(newValue);
    }

}
