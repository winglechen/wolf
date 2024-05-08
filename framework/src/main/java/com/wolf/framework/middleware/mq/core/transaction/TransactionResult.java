package com.wolf.framework.middleware.mq.core.transaction;

import lombok.Builder;
import lombok.Getter;
import com.wolf.framework.layer.api.DTO;

/**
 * com.wolf.framework.middleware.mq.domain.dto
 *
 * @author Wingle
 * @since 2021/12/1 上午12:26
 **/
@Builder
public class TransactionResult implements DTO {
    @Getter
    private TransactionStateEnum state;
    @Getter
    private Exception exception;

    public static TransactionResult COMMIT() {
        return TransactionResult.builder()
                .state(TransactionStateEnum.COMMIT)
                .build();
    }

    public static TransactionResult ROLLBACK() {
        return TransactionResult.builder()
                .state(TransactionStateEnum.ROLLBACK)
                .build();
    }

    public static TransactionResult TIMEOUT() {
        return TransactionResult.builder()
                .state(TransactionStateEnum.TIMEOUT)
                .build();
    }

    public static TransactionResult UNKNOWN() {
        return TransactionResult.builder()
                .state(TransactionStateEnum.UNKNOWN)
                .build();
    }

    public static TransactionResult COMMIT_HAS_EXCEPTION(Exception e) {
        return TransactionResult.builder()
            .state(TransactionStateEnum.COMMIT)
            .exception(e)
            .build();
    }

    public static TransactionResult ROLLBACK_HAS_EXCEPTION(Exception e) {
        return TransactionResult.builder()
            .state(TransactionStateEnum.ROLLBACK)
            .exception(e)
            .build();
    }

    public boolean hasException() {
        return null != exception;
    }
}
