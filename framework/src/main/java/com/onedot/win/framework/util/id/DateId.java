package com.onedot.win.framework.util.id;

import com.onedot.win.common.lang.contract.ID;

/**
 * com.onedot.win.framework.util.id
 *
 * @author Wingle
 * @since 2019/11/15 2:48 下午
 **/
public class DateId implements ID {
    private int category;
    private int dataCenterNo;
    private int shardingKey;
    private int shardingNums;

    public static DateId builder() {
        return new DateId();
    }

    DateId() {
        this.category = 0;
        this.dataCenterNo = 0;
        this.shardingKey = 0;
    }

    public DateId category(int category) {
        this.category = category;
        return this;
    }

    public DateId dataCenterNo(int dataCenterNo) {
        this.dataCenterNo = dataCenterNo;
        return this;
    }

    public DateId shardingKey(int key) {
        return this.shardingKey((long)key);
    }

    public DateId shardingKey(long key) {
        this.shardingKey = 0;
        return this;
    }

    //TODO
    public DateId shardingKey(String key) {

        return this;
    }

    public  String build() {
        return "";
    }


}
