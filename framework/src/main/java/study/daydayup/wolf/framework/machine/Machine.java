package study.daydayup.wolf.framework.machine;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 机器实体类
 *
 * @author: yik
 * @date: 2023-1-5
 */
@Data
@ToString
public class Machine {

    private Integer id;
    private String ip;
    private boolean deleteFlag;
    private int version;
    private LocalDateTime startedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
