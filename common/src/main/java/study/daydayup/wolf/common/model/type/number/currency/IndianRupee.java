package study.daydayup.wolf.common.model.type.number.currency;

import study.daydayup.wolf.common.lang.enums.currency.IndianRupeeEnum;
import study.daydayup.wolf.common.model.type.number.Decimal;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * study.daydayup.wolf.model.type.money
 * @author Wingle
 * @since 2019/10/15 12:48 下午
 **/
public class IndianRupee implements Currency {
    private Decimal value;
    private IndianRupeeEnum unit;
    
    public IndianRupee(long value, IndianRupeeEnum unit) {
        this.value = Decimal.of(value);
        this.unit = unit;
    }

    public IndianRupee(double value, int scale, IndianRupeeEnum unit) {
        this.value = Decimal.of(value, scale);
        this.unit = unit;
    }

    public IndianRupee(double value, IndianRupeeEnum unit) {
        this.value = Decimal.of(value);
        this.unit = unit;
    }
    
    public Decimal toRupee() {
        return convertTo(IndianRupeeEnum.RUPEE);
    }

    public Decimal toPaise() {
        return convertTo(IndianRupeeEnum.PAISE);
    }
    
    public Decimal toTenPaise() {
        return convertTo(IndianRupeeEnum.TEN_PAISE);
    }
    
    public Decimal toTenthPaise() {
        return convertTo(IndianRupeeEnum.TENTH_PAISE);
    }
    
    public Decimal toHundredthPaise() {
        return convertTo(IndianRupeeEnum.HUNDREDTH_PAISE);
    }

    public Decimal toThousandthPaise() {
        return convertTo(IndianRupeeEnum.THOUSANDTH_PAISE);
    }
    
    private Decimal convertTo(IndianRupeeEnum targetUnit) {
        if (this.unit.equals(targetUnit)) {
            return this.value;
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
