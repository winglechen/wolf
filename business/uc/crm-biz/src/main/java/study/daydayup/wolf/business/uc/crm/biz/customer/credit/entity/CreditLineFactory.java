package study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity
 *
 * @author Wingle
 * @since 2020/3/10 9:44 下午
 **/
public class CreditLineFactory {

    public static CreditLineEntity create(@NonNull Long accountId, @NonNull Long orgId, CreditConfig config) {
        CreditLine line = CreditLine.builder()
                .orgId(orgId)
                .accountId(accountId)
                .currency(config.getCurrency())
                .amount(BigDecimal.ZERO)

                .timesLatestDay(0)
                .timesLatestWeek(0)
                .timesLatestMonth(0)
                .timesLatestYear(0)
                .version(0)

                .build();

        return new CreditLineEntity(line, config, true);
    }
}
