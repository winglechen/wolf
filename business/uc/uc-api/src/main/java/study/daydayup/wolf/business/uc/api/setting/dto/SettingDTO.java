package study.daydayup.wolf.business.uc.api.setting.dto;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * study.daydayup.wolf.business.uc.api.setting.entity
 *
 * @author Wingle
 * @since 20L20L/1/1 12:25 下午
 **/
@Data
public class SettingDTO implements Model {
    private Long accountId;
    private Long orgId;


    private String key;
    private Object value;
    private Map<String, Object> map;
}
