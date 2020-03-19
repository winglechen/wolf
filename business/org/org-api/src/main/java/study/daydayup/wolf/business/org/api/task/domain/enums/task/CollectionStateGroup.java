package study.daydayup.wolf.business.org.api.task.domain.enums.task;

import study.daydayup.wolf.framework.layer.domain.Const;

import java.util.EnumSet;

/**
 * study.daydayup.wolf.business.org.api.task.domain.enums.task
 *
 * @author Wingle
 * @since 2020/3/19 2:26 下午
 **/
public class CollectionStateGroup implements Const {
    public static EnumSet<CollectionStateEnum> WORKING = EnumSet.of(
            CollectionStateEnum.WAIT_TO_PAY,
            CollectionStateEnum.PARTLY_PAID_QUESTIONED,
            CollectionStateEnum.PAID_QUESTIONED,
            CollectionStateEnum.FAILED_QUESTIONED
    );

    public static EnumSet<CollectionStateEnum> CONFIRMING = EnumSet.of(
            CollectionStateEnum.PARTLY_PAYING,
            CollectionStateEnum.PAYING,
            CollectionStateEnum.FAILING
    );

    public static EnumSet<CollectionStateEnum> CONFIRMED = EnumSet.of(
            CollectionStateEnum.PARTLY_PAID,
            CollectionStateEnum.PAID,
            CollectionStateEnum.FAILED
    );

    public static EnumSet<CollectionStateEnum> QUESTIONED = EnumSet.of(
            CollectionStateEnum.PARTLY_PAID_QUESTIONED,
            CollectionStateEnum.PAID_QUESTIONED,
            CollectionStateEnum.FAILED_QUESTIONED
    );

}
