package com.onedot.win.dts.transformer.mapper;


/**
 * com.onedot.win.dts.transformation.mapper
 *
 * @author Wingle
 * @since 2020/2/11 11:44 上午
 **/
public interface InitableMapper extends Mapper {
    void init(String column);

    void init(String column, String newColumn);
}
