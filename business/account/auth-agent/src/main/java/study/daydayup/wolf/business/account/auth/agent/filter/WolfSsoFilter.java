package study.daydayup.wolf.business.account.auth.agent.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * study.daydayup.wolf.business.account.auth.agent.filter
 *
 * @author Wingle
 * @since 2019/12/4 5:12 下午
 **/
public class WolfSsoFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(WolfSsoFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
