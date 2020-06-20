package study.daydayup.wolf.bigdata.datav.biz.api.daily;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.DateRangeRequest;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.SmsDateRequest;
import study.daydayup.wolf.bigdata.datav.api.dto.daily.TradeDateRequest;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyAudit;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyKoi;
import study.daydayup.wolf.bigdata.datav.api.service.daily.DailyKoiService;
import study.daydayup.wolf.bigdata.datav.biz.converter.daily.DailyKoiConverter;
import study.daydayup.wolf.bigdata.datav.biz.dal.dao.DailyKoiDAO;
import study.daydayup.wolf.bigdata.datav.biz.dal.dao.DailySmsDAO;
import study.daydayup.wolf.bigdata.datav.biz.dal.dao.DailyTradeDAO;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyKoiDO;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailySmsDO;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyTradeDO;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.state.NewState;
import study.daydayup.wolf.business.trade.api.domain.state.base.PaidState;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * study.daydayup.wolf.bigdata.datav.biz.api.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:08 上午
 **/
@RpcService
public class DailyKoiServiceImpl implements DailyKoiService {
    @Resource
    private DailyKoiDAO dailyKoiDAO;
    @Resource
    private DailyTradeDAO dailyTradeDAO;
    @Resource
    private DailySmsDAO dailySmsDAO;

    @Override
    public Result<Page<DailyKoi>> findByRange(@Validated DateRangeRequest request, @NonNull PageRequest pageReq) {
        Page<DailyKoi> result = selectByRange(request, pageReq);
        return Result.ok(result);
    }

    @Override
    public Result<Page<DailyAudit>> findAuditByRange(@Validated DateRangeRequest request, @NonNull PageRequest pageReq) {
        Page<DailyAudit> auditPage;

        Page<DailyKoi> koiPage = selectByRange(request, pageReq);
        if (CollectionUtil.isEmpty(koiPage.getData())) {
            auditPage = Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
            return Result.ok(auditPage) ;
        }

        List<DailyAudit> auditList = DailyKoiConverter.toAudit(koiPage.getData());
        mergeAuditTradeStatistics(auditList, request.getOrgId(), request.getSource());
        auditPage = koiPage.to(auditList);

        return Result.ok(auditPage) ;
    }

    private void mergeAuditTradeStatistics(@NonNull List<DailyAudit> auditList,@NonNull Long orgId, String source) {
        List<LocalDate> dateList = CollectionUtil.keys(auditList, DailyAudit::getDate);
        if (CollectionUtil.isEmpty(dateList)) {
            return;
        }

        mergeAuditPreviewCount(auditList, dateList, orgId, source);
        mergeAuditRequestCount(auditList, dateList, orgId, source);
        mergeAuditPaidCount(auditList, dateList, orgId, source);

        mergeVerifyCodeCount(auditList, dateList, orgId, source);
    }

    private void mergeVerifyCodeCount(List<DailyAudit> auditList, List<LocalDate> dateList, @NonNull Long orgId, String source) {
        int smsType = 1;
        SmsDateRequest request = createSmsDateRequest(dateList, orgId, smsType, source);
        List<DailySmsDO> smsList = dailySmsDAO.selectByDateIn(request);

        mergeSmsColumnToAudit(auditList, smsList, DailySmsDO::getSmsCount, DailyAudit::setVerifyCodeCount);
    }

    private void mergeAuditPreviewCount(List<DailyAudit> auditList, List<LocalDate> dateList, @NonNull Long orgId, String source) {
        int tradeType = TradeTypeEnum.LOAN_CONTRACT.getCode();
        int state = (new NewState()).getCode();

        TradeDateRequest request = createTradeDateRequest(dateList, orgId, tradeType, state, source);
        List<DailyTradeDO> tradeList = dailyTradeDAO.selectByDateIn(request);

        mergeTradeColumnToAudit(auditList, tradeList, DailyTradeDO::getTradeCount, DailyAudit::setAuditPreviewCount );
    }

    private void mergeAuditRequestCount(List<DailyAudit> auditList, List<LocalDate> dateList, @NonNull Long orgId, String source) {
        int tradeType = TradeTypeEnum.AUDIT_FEE.getCode();
        int state = (new NewState()).getCode();

        TradeDateRequest request = createTradeDateRequest(dateList, orgId, tradeType, state, source);
        List<DailyTradeDO> tradeList = dailyTradeDAO.selectByDateIn(request);

        mergeTradeColumnToAudit(auditList, tradeList, DailyTradeDO::getTradeCount, DailyAudit::setAuditRequestCount );
    }

    private void mergeAuditPaidCount(List<DailyAudit> auditList, List<LocalDate> dateList, @NonNull Long orgId, String source) {
        int tradeType = TradeTypeEnum.AUDIT_FEE.getCode();
        int state = (new PaidState()).getCode();

        TradeDateRequest request = createTradeDateRequest(dateList, orgId, tradeType, state, source);
        List<DailyTradeDO> tradeList = dailyTradeDAO.selectByDateIn(request);

        mergeTradeColumnToAudit(auditList, tradeList, DailyTradeDO::getTradeCount, DailyAudit::setAuditPaidCount);
        mergeTradeColumnToAudit(auditList, tradeList, DailyTradeDO::getTradeAmount, DailyAudit::setAuditPaidAmount);
    }

    private <V> void mergeTradeColumnToAudit(List<DailyAudit> auditList, List<DailyTradeDO> tradeList, Function<DailyTradeDO, V> getter, BiConsumer<DailyAudit, V> setter) {
        if (CollectionUtil.isEmpty(tradeList)) {
            return;
        }

        Map<Object, DailyTradeDO> tradeMap = CollectionUtil.map(tradeList,  DailyTradeDO::getDate);
        LocalDate k;
        DailyTradeDO v;
        for (DailyAudit audit : auditList) {
            k = audit.getDate();
            v = tradeMap.get(k);
            if (null == v) {
                continue;
            }

            setter.accept(audit, getter.apply(v));
        }
    }

    private <V> void mergeSmsColumnToAudit(List<DailyAudit> auditList, List<DailySmsDO> smsList, Function<DailySmsDO, V> getter, BiConsumer<DailyAudit, V> setter) {
        if (CollectionUtil.isEmpty(smsList)) {
            return;
        }

        Map<Object, DailySmsDO> tradeMap = CollectionUtil.map(smsList,  DailySmsDO::getDate);
        LocalDate k;
        DailySmsDO v;
        for (DailyAudit audit : auditList) {
            k = audit.getDate();
            v = tradeMap.get(k);
            if (null == v) {
                continue;
            }

            setter.accept(audit, getter.apply(v));
        }
    }

    private TradeDateRequest createTradeDateRequest(List<LocalDate> dateList, @NonNull Long orgId, int tradeType, int tradeState, String source) {
        TradeDateRequest request = TradeDateRequest.builder()
                .orgId(orgId)
                .tradeType(tradeType)
                .state(tradeState)
                .source(source)
                .build();
        request.addAll(dateList);

        return request;
    }

    private SmsDateRequest createSmsDateRequest(List<LocalDate> dateList, @NonNull Long orgId, int smsType, String source) {
        SmsDateRequest request = SmsDateRequest.builder()
                .orgId(orgId)
                .smsType(smsType)
                .source(source)
                .build();
        request.addAll(dateList);

        return request;
    }

    private Page<DailyKoi> selectByRange(@Validated DateRangeRequest request, @NonNull PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<DailyKoiDO> koiDOList = dailyKoiDAO.selectByDate(request);
        if (CollectionUtil.isEmpty(koiDOList)) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        List<DailyKoi> koiList = DailyKoiConverter.toModel(koiDOList);
        return Page.of(koiDOList).to(koiList);
    }
}
