package study.daydayup.wolf.business.uc.agent.setting;

import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.setting.entity.KvData;
import study.daydayup.wolf.business.uc.api.setting.service.CompanySettingService;
import study.daydayup.wolf.common.lang.ds.ObjectMap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * study.daydayup.wolf.business.uc.agent.setting
 *
 * @author Wingle
 * @since 2020/4/9 11:53 下午
 **/
@Component
public class CompanySettingAgent {
    private boolean isInit = false;
    private Set<String> changedNamespaceSet;
    private Map<String, ObjectMap> map;

    private long orgId;

    @Reference
    private CompanySettingService service;

    public void init( long orgId) {
        if (orgId <= 0) {
            throw new IllegalArgumentException("accountId and orgId can not less than 0");
        }

        if (isInit) {
            return;
        }

        this.orgId = orgId;
        changedNamespaceSet = new HashSet<>(4);
    }

    public Object get(@NonNull String key) {
        return get(key, KvData.DEFAULT_NAMESPACE);
    }

    public Object get(@NonNull String key, @NonNull String namespace) {
        if (null == map.get(namespace)) {
            return null;
        }

        return map.get(namespace).get(key);
    }

    public CompanySettingAgent set(@NonNull String key, @NonNull Object value) {
        return set(key, value, KvData.DEFAULT_NAMESPACE);
    }

    public CompanySettingAgent set(@NonNull String key, @NonNull Object value, @NonNull String namespace) {
        if (null == map.get(namespace)) {
            return this;
        }

        map.get(namespace).put(key, value);
        changedNamespaceSet.add(namespace);
        return this;
    }

    public void save() {
        if (null == changedNamespaceSet || changedNamespaceSet.isEmpty()) {
            return;
        }
    }

}
