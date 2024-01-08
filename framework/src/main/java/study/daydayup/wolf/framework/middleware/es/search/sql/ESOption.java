package study.daydayup.wolf.framework.middleware.es.search.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.api.Request;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ESOption implements Request {
    private String server;
    private String format;

    public String getFormat() {
        if (StringUtil.isBlank(format)) {
            format = "json";
        }

        return format;
    }

    public String getServer() {
        if (StringUtil.isBlank(server)) {
            server = "default";
        }

        return server;
    }

}
