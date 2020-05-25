package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.setting.api.dto.SettingDTO;
import study.daydayup.wolf.business.uc.setting.api.entity.CompanySetting;
import study.daydayup.wolf.business.uc.setting.api.entity.KvData;
import study.daydayup.wolf.business.uc.setting.api.service.CompanySettingService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.CompanySettingDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CompanySettingDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:38 下午
 **/
@RpcService(protocol = "dubbo")
public class CompanySettingServiceImpl implements CompanySettingService {
    @Resource
    private CompanySettingDAO companySettingDAO;
    @Override
    public Result<CompanySetting> find(Long companyId) {
        if (companyId == null) {
            return Result.fail(10000, "invalid args");
        }
        CompanySettingDO companySettingDO = companySettingDAO.findByNamespace(KvData.DEFAULT_NAMESPACE, companyId);
        if (companySettingDO == null) {
            return initSetting(companyId, KvData.DEFAULT_NAMESPACE);
        }
        CompanySetting setting = DOToModel(companySettingDO);
        return Result.ok(setting);
    }

    @Override
    public Result<Integer> save(@Validated CompanySetting companySetting) {
        int status;
        CompanySettingDO companySettingDO = companySettingDAO.findByNamespace(companySetting.getNamespace(), companySetting.getOrgId());
        if (companySettingDO == null) {
            status = companySettingDAO.insertSelective(modelToDO(companySetting));
            return Result.ok(status);
        }

        CompanySettingDO changedDO = modelToDO(companySetting);
        mergeDataChanges(changedDO, companySettingDO);

        status = companySettingDAO.updateByNamespace(changedDO, companySetting.getNamespace(), companySetting.getOrgId());
        return Result.ok(status);
    }

    @Override
    public Result<CompanySetting> findByNamespace(SettingDTO settingDTO) {
        settingDTO.valid();
        CompanySettingDO companySettingDO = companySettingDAO.findByNamespace(settingDTO.getNamespace(), settingDTO.getOrgId());
        if (companySettingDO == null) {
            return initSetting(settingDTO.getOrgId(), settingDTO.getNamespace());
        }
        CompanySetting setting = DOToModel(companySettingDO);
        return Result.ok(setting);
    }

    @Override
    public Result<List<CompanySetting>> findAll(@NonNull Long companyId) {
        List<CompanySettingDO> companySettingDOList = companySettingDAO.findByOrgId(companyId);

        List<CompanySetting> companySettingList = toModel(companySettingDOList);
        return Result.ok(companySettingList);
    }

    @Override
    public Result<List<CompanySetting>> findByOrgIds(Collection<Long> companyIds) {
        return findByOrgIds(KvData.DEFAULT_NAMESPACE, companyIds);
    }

    @Override
    public Result<List<CompanySetting>> findByOrgIds(@NonNull String namespace, Collection<Long> companyIds) {
        if (CollectionUtil.isEmpty(companyIds)) {
            return Result.ok(ListUtil.empty());
        }

        List<CompanySettingDO> companySettingDOList = companySettingDAO.findByOrgIdIn(namespace, companyIds);
        List<CompanySetting> companySettingList = toModel(companySettingDOList);

        return Result.ok(companySettingList);
    }

    private Result<CompanySetting> initSetting(Long companyId, String namespace) {
        CompanySetting status = new CompanySetting();
        status.setOrgId(companyId);
        status.setNamespace(namespace);
        status.setData("{}");

        CompanySettingDO statusDO = modelToDO(status);
        companySettingDAO.insertSelective(statusDO);

        return Result.ok(status);
    }

    private CompanySetting DOToModel(CompanySettingDO companySettingDO) {
        if (companySettingDO == null) {
            return null;
        }

        CompanySetting companySetting = new CompanySetting();
        BeanUtils.copyProperties(companySettingDO, companySetting);

        return companySetting;
    }

    private List<CompanySetting> toModel(List<CompanySettingDO> companySettingDOList ) {
        return CollectionUtil.to(companySettingDOList, this::DOToModel);
    }

    private CompanySettingDO modelToDO(CompanySetting companySetting) {
        if (companySetting == null) {
            return null;
        }

        CompanySettingDO companySettingDO = new CompanySettingDO();
        BeanUtils.copyProperties(companySetting, companySettingDO);

        return companySettingDO;
    }

    private void mergeDataChanges(CompanySettingDO changedDO, CompanySettingDO companySettingDO) {
        String changedData = changedDO.getData();
        if (StringUtil.isBlank(changedData)) {
            return;
        }

        String baseData = companySettingDO.getData();
        JSONObject baseJson = JSON.parseObject(baseData);
        if (baseJson.getInnerMap().isEmpty()) {
            return;
        }


        JSONObject changedJson = JSON.parseObject(changedData);
        Map<String, Object> map =changedJson.getInnerMap();

        for (String k : map.keySet()) {
            Object v = map.get(k);
            if (v == null) {
                continue;
            }

            baseJson.put(k, v);
        }

        changedDO.setData(JSON.toJSONString(baseJson));
    }
}
