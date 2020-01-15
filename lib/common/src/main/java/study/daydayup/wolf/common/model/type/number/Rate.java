package study.daydayup.wolf.common.model.type.number;

import study.daydayup.wolf.common.lang.enums.unit.RateEnum;
import study.daydayup.wolf.common.model.contract.DataType;
import study.daydayup.wolf.common.model.type.string.Decimal;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * study.daydayup.wolf.model.type.number
 *
 * @author Wingle
 * @since 2019/12/19 3:25 下午
 **/
public class Rate implements DataType {
    private Decimal value;
    private RateEnum unit;

    public Rate(int value, RateEnum unit) {
        this.value = Decimal.of(value);
        this.unit = unit;
    }

    public Rate(double value, RateEnum unit) {
        this.value = Decimal.of(value);
        this.unit = unit;
    }

    public Rate(double value, int scale, RateEnum unit) {
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
