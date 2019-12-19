package study.daydayup.wolf.common.model.type.currency;

import study.daydayup.wolf.common.lang.enums.currency.RMBEnum;
import study.daydayup.wolf.common.model.contract.Currency;
import study.daydayup.wolf.common.model.contract.DataType;

/**
 * study.daydayup.wolf.model.type.money
 * TODO: add BigDecimal support
 * @author Wingle
 * @since 2019/10/15 12:48 下午
 **/
public class RMB implements Currency, DataType {
    private long value;
    private RMBEnum unit;
    
    public RMB(long value, RMBEnum unit) {
        this.value = value;
        this.unit = unit;
    } 
    
    public long toYuan() {
        return convertTo(RMBEnum.YUAN);
    }

    public long toJiao() {
        return convertTo(RMBEnum.JIAO);
    }
    
    public long toFen() {
        return convertTo(RMBEnum.FEN);
    }
    
    public long toLi() {
        return convertTo(RMBEnum.LI);
    }
    
    public long toHao() {
        return convertTo(RMBEnum.HAO);
    }
    
    public long toSi() {
        return convertTo(RMBEnum.SI);
    }

    private long convertTo(RMBEnum targetUnit) {
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
