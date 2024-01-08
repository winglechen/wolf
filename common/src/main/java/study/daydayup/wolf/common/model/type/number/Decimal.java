package study.daydayup.wolf.common.model.type.number;

import lombok.NonNull;
import study.daydayup.wolf.common.model.contract.DataType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


/**
 * study.daydayup.wolf.common.model.type.string
 *
 * @author Wingle
 * @since 2020/1/12 1:03 上午
 **/
public class Decimal implements DataType {
    public static final BigDecimal ZERO = BigDecimal.ZERO;
    public static final BigDecimal ONE = BigDecimal.ONE;
    public static final BigDecimal TEN = BigDecimal.TEN;
    public static final BigDecimal HUNDRED = BigDecimal.valueOf(100);


    public static final int DEFAULT_SCALE   = 4;
    public static final int ZERO_SCALE      = 0;
    private BigDecimal value;



    public static Decimal of(int intNum) {
        return new Decimal(intNum);
    }

    public static Decimal of(long longNum) {
        return new Decimal(longNum);
    }

    public static Decimal of(@NonNull String strNum) {
        return of(strNum, DEFAULT_SCALE);
    }

    public static Decimal of(@NonNull String strNum, int scale) {
        return new Decimal(strNum, scale);
    }

    public static Decimal of(BigDecimal bigDecimal) {
        return of(bigDecimal, DEFAULT_SCALE);
    }

    public static Decimal of(BigDecimal bigDecimal, int scale) {
        return new Decimal(bigDecimal, scale);
    }

    public static Decimal of(double doubleValue) {
        return of(doubleValue, DEFAULT_SCALE);
    }

    public static Decimal of(double doubleValue, int scale) {
        return new Decimal(doubleValue, scale);
    }



    public Decimal(int intNum) {
        value = BigDecimal.valueOf(intNum);
        initScale(DEFAULT_SCALE);
    }

    public Decimal(long longNum) {
        value = BigDecimal.valueOf(longNum);
        initScale(DEFAULT_SCALE);
    }

    public Decimal(String strNum, int scale) {
        value = new BigDecimal(strNum);
        initScale(scale);
    }

    public Decimal(BigDecimal big, int scale) {
        value = big;
        initScale(scale);
    }

    public Decimal(double doubleValue, int scale) {
        value = BigDecimal.valueOf(doubleValue);
        initScale(scale);
    }

    public long toLong() {
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            return value.longValue();
        }
        BigDecimal num = value.setScale(0, RoundingMode.HALF_UP);
        return num.longValue();
    }

    public int toInt() {
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            return value.intValue();
        }

        BigDecimal num = value.setScale(0, BigDecimal.ROUND_HALF_UP);
        return num.intValue();
    }

    public void setValue(BigDecimal newValue) {
        if (newValue == null) {
            return;
        }
        value = newValue;
    }

    public BigDecimal toBigDecimal() {
        return value;
    }

    public String toString() {
        return value.toString();
    }

    public String toString(int scale) {
        if (scale < 0) {
            return toString();
        }

        return value.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Decimal decimal = (Decimal) o;
        return value.equals(decimal.toBigDecimal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void initScale(int scale) {
        if (scale <= 0) {
            scale = DEFAULT_SCALE;
        }
        value = value.setScale(scale, RoundingMode.HALF_UP);
    }
}
