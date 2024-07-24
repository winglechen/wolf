package com.wolf.framework.layer.domain.wolfno.config;

import com.wolf.framework.layer.domain.wolfno.WolfNo;
import com.wolf.framework.layer.domain.wolfno.wolfid.WolfID;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.no", ignoreInvalidFields = true)
public class WolfNoConfig implements Serializable {
    private boolean enabled = false;

    private List<WolfID> noList = new ArrayList<>();


    @Bean
    @Conditional(WolfNoCondition.class)
    @ConditionalOnBean(JdbcTemplate.class)
    public WolfNo wolfNo(JdbcTemplate jdbcTemplate) {
        WolfNo wolfNo = new WolfNo(this, jdbcTemplate);
        wolfNo.init();

        return wolfNo;
    }

}
