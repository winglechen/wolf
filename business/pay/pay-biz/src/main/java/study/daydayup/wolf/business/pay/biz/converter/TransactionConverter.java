package study.daydayup.wolf.business.pay.biz.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.pay.api.domain.entity.Transaction;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.TransactionDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.transaction.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class TransactionConverter implements Converter {
    public static Transaction toModel(TransactionDO transactionDO) {
        if (transactionDO == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDO, transaction);

        return transaction;
    }

    public static List<Transaction> toModel(List<TransactionDO> transactionDOList) {
        return CollectionUtil.to(transactionDOList, TransactionConverter::toModel);
    }

    public static TransactionDO toDO(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionDO transactionDO = new TransactionDO();
        BeanUtils.copyProperties(transaction, transactionDO);
        return transactionDO;
    }
}
