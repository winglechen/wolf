package study.daydayup.wolf.business.pay.biz.api;

import lombok.NonNull;
import study.daydayup.wolf.business.pay.api.domain.entity.Transaction;
import study.daydayup.wolf.business.pay.api.dto.base.manage.TransactionQuery;
import study.daydayup.wolf.business.pay.api.service.TransactionService;
import study.daydayup.wolf.business.pay.biz.converter.TransactionConverter;
import study.daydayup.wolf.business.pay.biz.dal.dao.TransactionDAO;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.TransactionDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/6/21 11:37 下午
 **/
@RpcService
public class TransactionServiceImpl implements TransactionService {
    @Resource
    private TransactionDAO transactionDAO;

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
        List<TransactionDO> transactionDOList = transactionDAO.selectByPaymentNoAndPayeeId(paymentNo, payeeId);
        if (CollectionUtil.isEmpty(transactionDOList)) {
            return Result.ok(Page.empty());
        }

        List<Transaction> transactionList = TransactionConverter.toModel(transactionDOList);
        return Result.ok(Page.one(transactionList));
    }

    public Result<Page<Transaction>> bySettlementNo(TransactionQuery query, PageRequest pageRequest) {
        Page.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<TransactionDO> transactionDOList = transactionDAO.selectBySettlementNoAndPayeeId(query.getSettlementNo(),query.getTransactionType(), query.getPayeeId());
        if (CollectionUtil.isEmpty(transactionDOList)) {
            return Result.ok(Page.empty());
        }

        List<Transaction> transactionList = TransactionConverter.toModel(transactionDOList);
        Page<Transaction> transactionPage = Page.of(transactionDOList).to(transactionList);
        return Result.ok(transactionPage);
    }

    public Result<Page<Transaction>> byType(TransactionQuery query, PageRequest pageRequest) {
        Page.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<TransactionDO> transactionDOList = transactionDAO.selectByRange(query);
        if (CollectionUtil.isEmpty(transactionDOList)) {
            return Result.ok(Page.empty());
        }

        List<Transaction> transactionList = TransactionConverter.toModel(transactionDOList);
        Page<Transaction> transactionPage = Page.of(transactionDOList).to(transactionList);
        return Result.ok(transactionPage);
    }
}
