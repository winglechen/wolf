package study.daydayup.wolf.business.union.api.controller;

import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.account.auth.agent.exception.SessionNotFoundException;
import study.daydayup.wolf.framework.layer.web.Controller;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.api.controller
 *
 * @author Wingle
 * @since 2019/12/12 3:57 下午
 **/
@RestController
public class BaseController implements Controller {
    @Resource
    protected Session session;

    protected  <T> T getFromSession(String key, Class<T> clazz) {
        return (T)getFromSession(key);
    }

    protected Object getFromSession(String key) {
        Object data = session.get(key);
        if (data == null) {
            throw new SessionNotFoundException(key);
        }

        return data;
    }


}
