package com.onedot.win.framework.middleware.es.search.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.layer.api.Request;

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
