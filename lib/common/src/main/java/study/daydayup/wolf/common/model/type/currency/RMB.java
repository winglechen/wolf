package study.daydayup.wolf.common.model.type.currency;

import study.daydayup.wolf.common.lang.enums.currency.RMBEnum;
import study.daydayup.wolf.common.model.contract.Currency;
import study.daydayup.wolf.common.model.type.string.Decimal;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * study.daydayup.wolf.model.type.money
 * @author Wingle
 * @since 2019/10/15 12:48 下午
 **/
public class RMB implements Currency {
    private Decimal value;
    private RMBEnum unit;
    
    public RMB(long value, RMBEnum unit) {
        this.value = Decimal.of(value);
        this.unit = unit;
    }

    public RMB(double value, int scale, RMBEnum unit) {
        this.value = Decimal.of(value, scale);
        this.unit = unit;
    }

    public RMB(double value, RMBEnum unit) {
        this.value = Decimal.of(value);
        this.unit = unit;
    }

    public Decimal toYuan() {
        return convertTo(RMBEnum.YUAN);
    }

    public Decimal toJiao() {
        return convertTo(RMBEnum.JIAO);
    }
    
    public Decimal toFen() {
        return convertTo(RMBEnum.FEN);
    }
    
    public Decimal toLi() {
        return convertTo(RMBEnum.LI);
    }
    
    public Decimal toHao() {
        return convertTo(RMBEnum.HAO);
    }
    
    public Decimal toSi() {
        return convertTo(RMBEnum.SI);
    }

    private Decimal convertTo(RMBEnum targetUnit) {
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
