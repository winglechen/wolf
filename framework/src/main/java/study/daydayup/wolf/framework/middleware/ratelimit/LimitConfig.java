package study.daydayup.wolf.framework.middleware.ratelimit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Config;

/**
 * study.daydayup.wolf.framework.middleware.ratelimit
 *
 * @author Wingle
 * @since 2020/10/16 2:25 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitConfig implements Config {

   private Integer maxRequestPerSecond;
   private Integer maxRequestPerMinute;
   private Integer maxRequestPerHour;

   private Integer maxRequestPerDay;
   private Integer maxRequestPerWeek;
   private Integer maxRequestPerMonth;
   private Integer maxRequestPerYear;

}
