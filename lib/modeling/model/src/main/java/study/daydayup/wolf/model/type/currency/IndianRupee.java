package study.daydayup.wolf.model.type.currency;

import study.daydayup.wolf.common.lang.enums.currency.IndianRupeeEnum;
import study.daydayup.wolf.model.contract.Currency;
import study.daydayup.wolf.model.contract.DataType;

/**
 * study.daydayup.wolf.model.type.money
 * TODO: add BigDecimal support
 * @author Wingle
 * @since 2019/10/15 12:48 下午
 **/
public class IndianRupee implements Currency, DataType {
    private long value;
    private IndianRupeeEnum unit;
    
    public IndianRupee(long value, IndianRupeeEnum unit) {
        this.value = value;
        this.unit = unit;
    } 
    
    public long toRupee() {
        return convertTo(IndianRupeeEnum.RUPEE);
    }

    public long toPaise() {
        return convertTo(IndianRupeeEnum.PAISE);
    }
    
    public long toTenPaise() {
        return convertTo(IndianRupeeEnum.TEN_PAISE);
    }
    
    public long toTenthPaise() {
        return convertTo(IndianRupeeEnum.TENTH_PAISE);
    }
    
    public long toHundredthPaise() {
        return convertTo(IndianRupeeEnum.HUNDREDTH_PAISE);
    }

    public long toThousandthPaise() {
        return convertTo(IndianRupeeEnum.THOUSANDTH_PAISE);
    }
    
    private long convertTo(IndianRupeeEnum targetUnit) {
        if (this.unit.equals(targetUnit)) {
            return this.value;
        }
        
        int sourceCode = this.unit.getCode();
        int targetCode = targetUnit.getCode();
        long step = targetCode - sourceCode;

        double newValue  = Math.pow(10,  step);
        newValue = value * newValue;

        return (long) newValue;
    }
}
