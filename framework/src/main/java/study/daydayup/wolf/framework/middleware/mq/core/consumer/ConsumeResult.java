package study.daydayup.wolf.framework.middleware.mq.core.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.DTO;
import study.daydayup.wolf.framework.middleware.mq.core.message.Message;

/**
 * study.daydayup.wolf.framework.middleware.mq.domain.dto
 *
 * @author Wingle
 * @since 2021/12/1 上午2:35
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeResult implements DTO {
    private ConsumeStateEnum state;

    //optional
    private Message lastRecord;

    public static ConsumeResult SUCCESS() {
        return ConsumeResult.builder()
                .state(ConsumeStateEnum.SUCCESS)
                .build();
    }

    public static ConsumeResult SUCCESS(Message lastRecord) {
        return ConsumeResult.builder()
                .state(ConsumeStateEnum.SUCCESS)
                .lastRecord(lastRecord)
                .build();
    }

    public static ConsumeResult FAILURE() {
        return ConsumeResult.builder()
                .state(ConsumeStateEnum.FAILURE)
                .build();
    }

    public static ConsumeResult LATER() {
        return ConsumeResult.builder()
                .state(ConsumeStateEnum.LATER)
                .build();
    }
}
