package com.wolf.framework.middleware.binlog.mysql;

import com.alibaba.dts.formats.avro.Field;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * MysqlFieldConverter
 *
 * @author: YIK
 * @since: 2022/3/1 10:18 AM
 */
@Slf4j
public class MysqlFieldConverter implements FieldConverter {
    static DataAdapter[] DATA_ADAPTER = new DataAdapter[256];

    static {
        DATA_ADAPTER[0] = new DecimalStringAdapter(); //Type.DECIMAL
        DATA_ADAPTER[1] = new NumberStringAdapter(); //Type.INT8;
        DATA_ADAPTER[2] = new NumberStringAdapter(); //Type.INT16;
        DATA_ADAPTER[3] = new NumberStringAdapter(); //Type.INT32;

        DATA_ADAPTER[4] = new DoubleStringAdapter(); //Type.FLOAT
        DATA_ADAPTER[5] = new DoubleStringAdapter(); //Type.DOUBLE

        DATA_ADAPTER[6] = new UTF8StringEncodeAdapter(); //Type.NULL

        DATA_ADAPTER[7] = new TimestampStringAdapter(); //Type.TIMESTAMP
        DATA_ADAPTER[8] = new NumberStringAdapter(); //Type.INT64
        DATA_ADAPTER[9] = new NumberStringAdapter(); //Type.INT24

        DATA_ADAPTER[10] = new DateAdapter(); //Type.DATE
        DATA_ADAPTER[11] = new TimeAdapter(); //Type.TIME
        DATA_ADAPTER[12] = new DateTimeAdapter(); //Type.DATETIME
        DATA_ADAPTER[13] = new YearAdapter(); //Type.YEAR
        DATA_ADAPTER[14] = new DateTimeAdapter(); //Type.DATETIME
        DATA_ADAPTER[15] = new CharacterAdapter();    //Type.STRING
        DATA_ADAPTER[16] = new NumberStringAdapter(); //Type.BIT

        DATA_ADAPTER[255] = new GeometryAdapter();    //Type.GEOMETRY;
        DATA_ADAPTER[254] = new CharacterAdapter(); //Type.STRING;
        DATA_ADAPTER[253] = new CharacterAdapter(); //Type.STRING;

        DATA_ADAPTER[252] = new BinaryAdapter(); //Type.BLOB;
        DATA_ADAPTER[251] = new BinaryAdapter(); //Type.BLOB;
        DATA_ADAPTER[250] = new BinaryAdapter(); //Type.BLOB;
        DATA_ADAPTER[249] = new BinaryAdapter(); //Type.BLOB;

        DATA_ADAPTER[246] = new DecimalStringAdapter(); //Type.DECIMAL;

        DATA_ADAPTER[248] = new TextObjectAdapter(); //Type.SET;
        DATA_ADAPTER[247] = new TextObjectAdapter(); //Type.ENUM;
        DATA_ADAPTER[245] = new TextObjectAdapter();  //Type.JSON;
    }

    @Override
    public FieldValue convert(Field field, Object o) throws Exception {
        DataAdapter dataAdapter = null;
        try {
            dataAdapter = DATA_ADAPTER[field.getDataTypeNumber()];
            return dataAdapter.getFieldValue(o);
        } catch (Exception e) {
            log.error("can not convert field:{}, object:{}, dataAdapter{}, typeNumber:{}", field, o, dataAdapter, field.getDataTypeNumber());
            return null;
        }

    }


    static interface DataAdapter<T> {
        FieldValue getFieldValue(Object data);
    }


    static class UTF8StringEncodeAdapter implements DataAdapter {
        @Override
        public FieldValue getFieldValue(Object data) {

            FieldValue<String> fieldValue = new FieldValue<String>();
            if (null != data) {
                byte[] bytes = ((String) data).getBytes(UTF_8);
                fieldValue.setValue(bytes);
                fieldValue.setReal((String) data);
            }
            fieldValue.setEncoding("UTF8");
            return fieldValue;
        }
    }

    static class NumberStringAdapter implements DataAdapter {

        @Override
        public FieldValue getFieldValue(Object data) {
            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.Integer integer = (com.alibaba.dts.formats.avro.Integer) data;
                fieldValue.setValue(integer.getValue().getBytes(US_ASCII));
                //must do it as follows!
                switch (integer.getPrecision()) {
                    case 1:
                        fieldValue.setReal(Byte.valueOf(integer.getValue()));
                    case 2:
                        fieldValue.setReal(Short.valueOf(integer.getValue()));
                    case 8:
                        fieldValue.setReal(Long.valueOf(integer.getValue()));
                    default:
                        fieldValue.setReal(Integer.valueOf(integer.getValue()));
                }

            }
            fieldValue.setEncoding("ASCII");
            return fieldValue;
        }
    }

    static class DecimalStringAdapter implements DataAdapter {
        @Override
        public FieldValue getFieldValue(Object data) {
            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.Decimal decimal = (com.alibaba.dts.formats.avro.Decimal) data;
                fieldValue.setValue(decimal.getValue().getBytes(US_ASCII));
                fieldValue.setReal(new BigDecimal(decimal.getValue()));
            }
            fieldValue.setEncoding("ASCII");
            return fieldValue;
        }
    }

    static class DoubleStringAdapter implements DataAdapter {
        @Override
        public FieldValue getFieldValue(Object data) {
            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.Float aFloat = (com.alibaba.dts.formats.avro.Float) data;
                fieldValue.setValue(Double.toString(aFloat.getValue()).getBytes(US_ASCII));
                fieldValue.setReal(Double.toString(aFloat.getValue()));
            }
            fieldValue.setEncoding("ASCII");
            return fieldValue;
        }
    }

    static class TimestampStringAdapter implements DataAdapter {

        static String[] MILLIS_PREFIX = new String[]{"", "0", "00", "000", "0000", "00000", "000000"};

        @Override
        public FieldValue getFieldValue(Object data) {
            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                StringBuilder timestampBuilder = new StringBuilder(64);

                com.alibaba.dts.formats.avro.Timestamp timestamp = (com.alibaba.dts.formats.avro.Timestamp) data;

                timestampBuilder.append(timestamp.getTimestamp());
                if (null != timestamp.getMillis()) {
                    timestampBuilder.append('.');
                    String millis = timestamp.getMillis() + "";
                    timestampBuilder.append(MILLIS_PREFIX[6 - millis.length()]).append(millis);
                }

                fieldValue.setValue(timestampBuilder.toString().getBytes(US_ASCII));
                fieldValue.setReal(timestamp.getTimestamp());
            }
            fieldValue.setEncoding("ASCII");
            return fieldValue;
        }
    }

    static abstract class AbstractDateTimeAdapter implements DataAdapter {

        void encodeDate(com.alibaba.dts.formats.avro.DateTime dateTime, byte[] out, int position) {
            if (null != dateTime && null != out) {
                out[position] = (byte) ('0' + (dateTime.getYear() / 1000));
                out[position + 1] = (byte) ('0' + (dateTime.getYear() % 1000 / 100));
                out[position + 2] = (byte) ('0' + (dateTime.getYear() % 100 / 10));
                out[position + 3] = (byte) ('0' + (dateTime.getYear() % 10));
                out[position + 4] = '-';
                out[position + 5] = (byte) ('0' + (dateTime.getMonth() / 10));
                out[position + 6] = (byte) ('0' + (dateTime.getMonth() % 10));
                out[position + 7] = '-';
                out[position + 8] = (byte) ('0' + (dateTime.getDay() / 10));
                out[position + 9] = (byte) ('0' + (dateTime.getDay() % 10));
            }
        }

        void encodeTime(com.alibaba.dts.formats.avro.DateTime dateTime, byte[] out, int position) {
            if (null != dateTime && null != out) {
                out[position + 0] = (byte) ('0' + (dateTime.getHour() / 10));
                out[position + 1] = (byte) ('0' + (dateTime.getHour() % 10));
                out[position + 2] = ':';
                out[position + 3] = (byte) ('0' + (dateTime.getMinute() / 10));
                out[position + 4] = (byte) ('0' + (dateTime.getMinute() % 10));
                out[position + 5] = ':';
                out[position + 6] = (byte) ('0' + (dateTime.getSecond() / 10));
                out[position + 7] = (byte) ('0' + (dateTime.getSecond() % 10));
            }
        }

        void encodeTimeMillis(com.alibaba.dts.formats.avro.DateTime dateTime, byte[] out, int position) {
            if (null != dateTime.getMillis() && 0 != dateTime.getMillis()) {
                int mills = dateTime.getMillis();
                out[position] = '.';
                out[position + 1] = (byte) ('0' + (mills / 100000));
                mills %= 100000;
                out[position + 2] = (byte) ('0' + (mills / 10000));
                mills %= 10000;
                out[position + 3] = (byte) ('0' + (mills / 1000));
                mills %= 1000;
                out[position + 4] = (byte) ('0' + (mills / 100));
                mills %= 100;
                out[position + 5] = (byte) ('0' + (mills / 10));
                out[position + 6] = (byte) ('0' + (mills % 10));
            }
        }
    }

    static class DateAdapter extends AbstractDateTimeAdapter {

        @Override
        public FieldValue<LocalDate> getFieldValue(Object data) {

            FieldValue<LocalDate> fieldValue = new FieldValue<LocalDate>();
            if (null != data) {
                com.alibaba.dts.formats.avro.DateTime dateTime = (com.alibaba.dts.formats.avro.DateTime) data;

                byte[] date = new byte[10];
                encodeDate(dateTime, date, 0);
                LocalDate localDate = LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());
                fieldValue.setValue(date);
                fieldValue.setReal(localDate);
            }

            fieldValue.setEncoding("ASCII");
            return fieldValue;
        }
    }


    static class TimeAdapter extends AbstractDateTimeAdapter {

        @Override
        public FieldValue getFieldValue(Object data) {

            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.DateTime dateTime = (com.alibaba.dts.formats.avro.DateTime) data;

                // 判断是否是负数
                int head = 0;
                if (dateTime.getHour() <= -100) {
                    head = 2;
                } else if ((dateTime.getHour() >= 100) || (dateTime.getHour() < 0) || (dateTime.getMinute() < 0) || (dateTime.getSecond() < 0) || ((null != dateTime.getMillis()) && (dateTime.getMillis() < 0))) {
                    head = 1;
                }
                byte[] time;
                // 毫秒位0忽略
                if (null == dateTime.getMillis() || 0 == dateTime.getMillis()) {
                    time = new byte[8 + head];
                } else {
                    time = new byte[15 + head];
                }

                int index = 0;
                if (head > 0 && dateTime.getHour() <= 0) {
                    dateTime.setHour(-dateTime.getHour());
                    dateTime.setMinute(-dateTime.getMinute());
                    dateTime.setSecond(-dateTime.getSecond());
                    if (null != dateTime.getMillis()) {
                        dateTime.setMillis(-dateTime.getMillis());
                    }

                    time[index++] = '-';
                }

                if (dateTime.getHour() >= 100) {
                    time[index++] = (byte) ('0' + (dateTime.getHour() / 100));
                    dateTime.setHour(dateTime.getHour() % 100);
                }

                encodeTime(dateTime, time, index);
                encodeTimeMillis(dateTime, time, index + 8);

                fieldValue.setValue(time);
                LocalDateTime localDateTime = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond(), dateTime.getMillis());
                fieldValue.setReal(localDateTime);
            }

            fieldValue.setEncoding("ASCII");
            return fieldValue;
        }
    }

    static class DateTimeAdapter extends AbstractDateTimeAdapter {

        @Override
        public FieldValue getFieldValue(Object data) {

            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.DateTime dateTime = (com.alibaba.dts.formats.avro.DateTime) data;

                byte[] time = null;
                //忽略毫秒位值是0
                if (null == dateTime.getMillis() || 0 == dateTime.getMillis()) {
                    time = new byte[19];
                } else {
                    time = new byte[26];
                }
                encodeDate(dateTime, time, 0);
                time[10] = ' ';
                encodeTime(dateTime, time, 11);
                encodeTimeMillis(dateTime, time, 19);

                fieldValue.setValue(time);
                LocalDateTime localDateTime = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond(), dateTime.getMillis());
                fieldValue.setReal(localDateTime);
            }

            fieldValue.setEncoding("ASCII");
            return fieldValue;
        }

    }


    static class YearAdapter implements DataAdapter {

        @Override
        public FieldValue getFieldValue(Object data) {

            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.DateTime dateTime = (com.alibaba.dts.formats.avro.DateTime) data;
                fieldValue.setValue(Integer.toString(dateTime.getYear()).getBytes(US_ASCII));
                LocalDateTime localDateTime = LocalDateTime.of(dateTime.getYear(), 1, 1, 0, 0, 0, 0);
                fieldValue.setReal(localDateTime);
            }

            fieldValue.setEncoding("ASCII");
            return fieldValue;
        }

    }

    static class CharacterAdapter implements DataAdapter {

        @SneakyThrows
        @Override
        public FieldValue getFieldValue(Object data) {

            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.Character character = (com.alibaba.dts.formats.avro.Character) data;
                fieldValue.setValue(character.getValue().array());
                fieldValue.setEncoding(character.getCharset());
                fieldValue.setReal(new String(fieldValue.getValue(), JDKEncodingMapper.getJDKEncoding(character.getCharset())));
            } else {
                fieldValue.setEncoding("ASCII");
            }
            return fieldValue;
        }

    }

    static class GeometryAdapter implements DataAdapter {

        @Override
        public FieldValue getFieldValue(Object data) {

            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.BinaryGeometry geometry = (com.alibaba.dts.formats.avro.BinaryGeometry) data;
                fieldValue.setValue(geometry.getValue().array());
            }
            return fieldValue;
        }
    }


    static class BinaryAdapter implements DataAdapter {

        @Override
        public FieldValue getFieldValue(Object data) {

            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.BinaryObject binaryObject = (com.alibaba.dts.formats.avro.BinaryObject) data;
                fieldValue.setValue(binaryObject.getValue().array());
                fieldValue.setReal(fieldValue.getValue());
            }

            return fieldValue;
        }
    }


    static class TextObjectAdapter implements DataAdapter {

        @Override
        public FieldValue getFieldValue(Object data) {

            FieldValue fieldValue = new FieldValue();
            if (null != data) {
                com.alibaba.dts.formats.avro.TextObject textObject = (com.alibaba.dts.formats.avro.TextObject) data;
                byte[] bytes = textObject.getValue().getBytes(UTF_8);
                fieldValue.setValue(bytes);
                fieldValue.setReal(new String(bytes, UTF_8));
            }
            fieldValue.setEncoding("UTF8");
            return fieldValue;
        }
    }
}
