package study.daydayup.wolf.business.uc.api.setting.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.common.util.collection.ArrayUtil;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.api.Model;

import java.util.*;

/**
 * study.daydayup.wolf.business.uc.api.setting.entity
 *
 * @author Wingle
 * @since 20L20L/1/1 12:25 下午
 **/
@Data
@Builder
public class SettingDTO implements Model {
    private Long accountId;
    private Long orgId;
    private Collection<String> namespaces;
    private String namespace;

    private Map<String, Object> map;

    public void valid() {
        valid(false);
    }

    public void valid(boolean needBothAccount) {
        if (needBothAccount) {
            if (null == accountId || null == orgId) {
                throw new IllegalArgumentException("accountId and orgId can't be null");
            }
        }

        if (!needBothAccount) {
            if (null == accountId && null == orgId) {
                throw new IllegalArgumentException("accountId and orgId can't be null");
            }
        }

        if (StringUtil.isEmpty(namespace) && CollectionUtil.isEmpty(namespaces)) {
            throw new IllegalArgumentException("namespace can't be null");
        }

        format();
    }

    public void format() {
        if (StringUtil.notBlank(namespace)) {
            initNamespaces();
            addNamespace(namespace);
            namespace = null;
        }
    }

    public SettingDTO addNamespace(String... keyArray) {
        if (ArrayUtil.isEmpty(keyArray)) {
            return this;
        }

        initNamespaces();
        namespaces.addAll(Arrays.asList(keyArray));
        return this;
    }

    public SettingDTO set(@NonNull String key, @NonNull Object value) {
        initMap();
        map.put(key, value);

        return this;
    }

    public void initNamespaces() {
        if (namespaces != null) {
            return;
        }

        namespaces = new ArrayList<>(4);
    }

    public void initMap() {
        if (map != null) {
            return;
        }
        map = new HashMap<>(8);
    }
}
