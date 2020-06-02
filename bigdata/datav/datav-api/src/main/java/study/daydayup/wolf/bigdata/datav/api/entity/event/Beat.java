package study.daydayup.wolf.bigdata.datav.api.entity.event;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Beat implements Serializable {
    private Long id;

    private Long orgId;
    private Long accountId;

    private String deviceId;
    private String deviceType;

    private String ip;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @NotBlank
    private String eventType;
    private String event;
    private String eventContext;
    private String eventMemo;

    private LocalDateTime createdAt;
}