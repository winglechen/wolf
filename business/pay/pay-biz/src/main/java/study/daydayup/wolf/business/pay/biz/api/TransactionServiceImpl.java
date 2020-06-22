package study.daydayup.wolf.business.pay.biz.api;

import lombok.NonNull;
import study.daydayup.wolf.business.pay.api.domain.entity.Transaction;
import study.daydayup.wolf.business.pay.api.dto.base.manage.TransactionQuery;
import study.daydayup.wolf.business.pay.api.service.TransactionService;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/6/21 11:37 下午
 **/
@RpcService
public class TransactionServiceImpl implements TransactionService {
    @Override
    public Result<Page<Transaction>> query(TransactionQuery query, PageRequest pageRequest) {
        if (StringUtil.notBlank(query.getPaymentNo())) {
            return byPaymentNo(query.getPaymentNo(), query.getPayeeId());
        }

        if (StringUtil.notBlank(query.getSettlementNo())) {
            return bySettlementNo(query, pageRequest);
        }

        return byType(query, pageRequest);
    }

    public Result<Page<Transaction>> byPaymentNo(@NonNull String paymentNo, @NonNull Long payeeId) {
        return null;
    }

    public Result<Page<Transaction>> bySettlementNo(TransactionQuery query, PageRequest pageRequest) {
        return null;
    }

    public Result<Page<Transaction>> byType(TransactionQuery query, PageRequest pageRequest) {
        return null;
    }
}
