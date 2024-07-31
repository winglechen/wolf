package com.wolf.framework.layer.web.session;

import com.wolf.common.contract.container.Context;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@Data
public class SessionContext implements Context {

    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;

}
