package study.daydayup.wolf.business.uc.api.notice.domain.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

/**
 * study.daydayup.wolf.business.uc.api.notice.domain.entity
 *
 * @author Wingle
 * @since 2020/3/20 5:11 下午
 **/
@Data
public class SMS implements Model {
    private String mobile;
    private String msg;
}
