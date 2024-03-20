package com.onedot.win.framework.middleware.ratelimit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.onedot.win.framework.layer.api.Config;

/**
 * com.onedot.win.framework.middleware.ratelimit
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
