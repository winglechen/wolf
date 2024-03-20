package com.onedot.win.framework.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * com.onedot.win.framework.util
 *
 * @author Wingle
 * @since 2021/3/22 10:46 下午
 **/
@Component
public class SandboxUtil {

    @Value("${wolf.sandbox.enable:false}")
    private boolean sandboxEnv;

    public boolean isSandboxEnv() {
        return sandboxEnv;
    }

}
