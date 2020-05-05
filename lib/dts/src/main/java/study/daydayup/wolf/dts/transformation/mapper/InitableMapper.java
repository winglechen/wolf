package study.daydayup.wolf.dts.transformation.mapper;


/**
 * study.daydayup.wolf.dts.transformation.mapper
 *
 * @author Wingle
 * @since 2020/2/11 11:44 上午
 **/
public interface InitableMapper extends Mapper {
    void init(String column);
    void init(String column, String newColumn);
}
