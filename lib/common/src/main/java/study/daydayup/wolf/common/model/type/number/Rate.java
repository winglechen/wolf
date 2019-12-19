package study.daydayup.wolf.common.model.type.number;

import study.daydayup.wolf.common.lang.enums.unit.RateEnum;
import study.daydayup.wolf.common.model.contract.DataType;

/**
 * study.daydayup.wolf.model.type.number
 *
 * @author Wingle
 * @since 2019/12/19 3:25 下午
 **/
public class Rate implements DataType {
    private int value;
    private RateEnum unit;

    public Rate(int value, RateEnum unit) {
        this.value = value;
        this.unit = unit;
    }

    public int toHundred() {
        return convertTo(RateEnum.PER_HUNDRED);
    }

    public int toThousand() {
        return convertTo(RateEnum.PER_THOUSAND);
    }

    public int toTenThousand() {
        return convertTo(RateEnum.PRE_TEN_THOUSAND);
    }

    public int toHundredThousand() {
        return convertTo(RateEnum.PRE_HUNDRED_THOUSAND);
    }

    public int toMillion() {
        return convertTo(RateEnum.PER_MILLION);
    }

    private int convertTo(RateEnum targetUnit) {
        if (unit.equals(targetUnit)) {
            return value;
        }

        int sourceCode = this.unit.getCode();
        int targetCode = targetUnit.getCode();
        int step = targetCode - sourceCode;

        double diff  = Math.pow(10,  step);
        double newValue = value * diff;

        return (int)newValue;
    }

}
