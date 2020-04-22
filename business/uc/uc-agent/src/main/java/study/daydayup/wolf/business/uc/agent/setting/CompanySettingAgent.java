package study.daydayup.wolf.business.uc.agent.setting;

import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.setting.dto.SettingDTO;
import study.daydayup.wolf.business.uc.api.setting.entity.CompanySetting;
import study.daydayup.wolf.business.uc.api.setting.entity.KvData;
import study.daydayup.wolf.business.uc.api.setting.service.CompanySettingService;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.util.HashMap;
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
    private String currentNamespace;
    private Map<String, ObjectMap> map;

    private long orgId;

    @Reference
    private CompanySettingService service;

    public void init(long orgId) {
        if (orgId <= 0) {
            throw new IllegalArgumentException("accountId and orgId can not less than 0");
        }

        if (isInit) {
            return;
        }

        this.orgId = orgId;
        changedNamespaceSet = new HashSet<>(8);
        currentNamespace = KvData.DEFAULT_NAMESPACE;
        map = new HashMap<>();

        initNamespace(currentNamespace);
        isInit = true;
    }

    public void defaultNamespace() {
        currentNamespace = KvData.DEFAULT_NAMESPACE;
    }

    public void namespace(@NonNull String namespace) {
        currentNamespace = namespace;
        initNamespace(currentNamespace);
    }

    public ObjectMap getAll() {
        return getAll(currentNamespace);
    }

    public ObjectMap getAll(@NonNull String namespace) {
        return map.get(namespace);
    }

    public Object get(@NonNull String key) {
        return get(key, currentNamespace);
    }

    public Object get(@NonNull String key, @NonNull String namespace) {
        return map.get(namespace).get(key);
    }

    public CompanySettingAgent set(@NonNull String key, @NonNull Object value) {
        return set(key, value, currentNamespace);
    }

    public CompanySettingAgent set(@NonNull String key, @NonNull Object value, @NonNull String namespace) {
        if (null == map.get(namespace)) {
            map.put(namespace, new ObjectMap());
        }

        map.get(namespace).put(key, value);
        changedNamespaceSet.add(namespace);
        return this;
    }

    public CompanySettingAgent setAll(@NonNull Map<String, Object> kv) {
        return setAll(kv, currentNamespace);
    }

    public CompanySettingAgent setAll(@NonNull Map<String, Object> kv, @NonNull String namespace) {
        if (kv.isEmpty()) {
            return this;
        }

        if (null == map.get(namespace)) {
            map.put(namespace, new ObjectMap());
        }

        map.get(namespace).putAll(kv);
        changedNamespaceSet.add(namespace);
        return this;
    }

    public void save() {
        if (orgId <= 0) {
            throw new IllegalArgumentException("accountId and orgId can not less than 0");
        }

        if (CollectionUtil.isEmpty(changedNamespaceSet)) {
            return;
        }

        for (String namespace : changedNamespaceSet) {
            saveByNamespace(namespace);
        }
    }


    private void saveByNamespace(@NonNull String namespace) {
        if (MapUtil.isEmpty(map.get(namespace))) {
            return;
        }

        CompanySetting setting = CompanySetting.builder()
                .orgId(orgId)
                .namespace(namespace)
                .data(JSON.toJSONString(map.get(namespace)))
                .build();

        service.save(setting);
    }

    private void initNamespace(@NonNull String namespace) {
        if (orgId <= 0) {
            throw new IllegalArgumentException("accountId and orgId can not less than 0");
        }

        if (null != map.get(namespace)) {
            return;
        }

        findByNamespace(namespace);
    }

    private void findByNamespace(@NonNull String namespace) {
        SettingDTO query = SettingDTO.builder()
                .orgId(orgId)
                .namespace(namespace)
                .build();

        CompanySetting setting = service.findByNamespace(query).getData();
        if (setting == null || StringUtil.isBlank(setting.getData())) {
            map = MapUtil.empty();
            return;
        }

        ObjectMap data = JSON.parseObject(setting.getData(), ObjectMap.class);
        map.put(namespace, data);
    }

}
