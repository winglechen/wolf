package study.daydayup.wolf.bigdata.datav.api.entity.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beat implements Serializable {
    private Long id;

    private Long orgId;
    private Long accountId;
    private String sessionId;

    private String deviceId;
    private String deviceType;

    private String ip;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @NotBlank
    private String eventType;

    private String source;
    private Long objectId;
    private Integer objectType;

    private String event;
    private String eventContext;
    private String eventMemo;

    private LocalDateTime createdAt;
}