package study.daydayup.wolf.business.goods.api.service;

import study.daydayup.wolf.business.goods.api.dto.request.LoanCreateRequest;
import study.daydayup.wolf.business.goods.api.entity.Loan;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.service
 *
 * @author Wingle
 * @since 2019/12/10 8:47 下午
 **/
public interface LoanService {
    long create(LoanCreateRequest request);
    int modify(Loan loan);
    List<Loan> findByOrgId(long orgId);
}
