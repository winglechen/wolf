package com.onedot.win.framework.layer.context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.onedot.win.common.lang.contract.Context;

import java.time.LocalDateTime;

/**
 * com.onedot.win.framework.layer.context
 *
 * @author Wingle
 * @since 2020/1/9 9:08 下午
 **/
@Component
@Scope("request")
public class WebContext implements Context {
    private LocalDateTime requestTime;

    public LocalDateTime getRequestTime() {
        if (requestTime == null) {
            requestTime = LocalDateTime.now();
        }

        return requestTime;
    }
}
