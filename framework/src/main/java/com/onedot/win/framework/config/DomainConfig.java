package com.onedot.win.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import com.onedot.win.common.util.collection.MapUtil;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.layer.api.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author Wingle
 * @since 2020/10/19 10:19 下午
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.domain")
public class DomainConfig implements Config {
    private Map<String, String> domainMap;
    private Map<String, List<String>> keyMap;
    private Map<String, Long> orgIdMap;

    public Long getOrgIdByKey(String key) {
        if (StringUtil.isBlank(key)) {
            throw new IllegalArgumentException("key can't be blank");
        }

        if (null == orgIdMap) {
            return null;
        }

        return orgIdMap.get(key);
    }

    public Long getOrgIdByDomain(String domain) {
        initDomainMap();

        String key = domainMap.get(domain);
        if (key == null) {
            return null;
        }

        return getOrgIdByKey(key);
    }

    private void initDomainMap() {
        if (domainMap != null) {
            return;
        }

        domainMap = new HashMap<>();
        if (MapUtil.isEmpty(keyMap)) {
            return;
        }

        for (Map.Entry<String, List<String>> entry: keyMap.entrySet() ) {
            for (String domain: entry.getValue()) {
                domainMap.put(domain, entry.getKey());
            }
        }
    }
}
