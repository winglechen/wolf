package study.daydayup.wolf.model.type.money;

import study.daydayup.wolf.model.enums.RMBEnum;

/**
 * study.daydayup.wolf.model.type.money
 *
 * @author Wingle
 * @since 2019/10/15 12:48 下午
 **/
public class RMB extends Money {
    private long value;
    private RMBEnum unit;
    
    public RMB(long value, RMBEnum unit) {
        this.value = value;
        this.unit = unit;
    } 
    
    public long toYuan() {
        RMBEnum targetUnit = RMBEnum.YUAN;
        return convert(targetUnit);
    }
    
    public long toFen() {
        RMBEnum targetUnit = RMBEnum.FEN;
        return convert(targetUnit);
    }
    
    public long toLi() {
        RMBEnum targetUnit = RMBEnum.LI;
        return convert(targetUnit);
    }
    
    public long toHao() {
        RMBEnum targetUnit = RMBEnum.HAO;
        return convert(targetUnit);
    }
    
    public long toSi() {
        RMBEnum targetUnit = RMBEnum.SI;
        return convert(targetUnit);
    }
    
    private long convert(RMBEnum targetUnit) {
        if(this.unit.equals(targetUnit)) {
            return this.value;
        }
        
        int sourceCode = this.unit.getCode();
        int targetCode = targetUnit.getCode();
        long diff = targetCode - sourceCode;
        diff  = (long) Math.pow(10,  diff);

        return this.value * diff;
    }
}
