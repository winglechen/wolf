package study.daydayup.wolf.framework.layer.converter;

import study.daydayup.wolf.framework.layer.api.Model;
import study.daydayup.wolf.framework.layer.api.Response;

/**
 * study.daydayup.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:05 下午
 **/
public interface ResponseConverter extends Converter {
    Model toModel(Response response);
}
