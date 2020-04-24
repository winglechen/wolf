package study.daydayup.wolf.business.goods.biz.loan;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.exception.FailedCreateLoanException;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.goods.biz.dal.dao.GoodsDAO;
import study.daydayup.wolf.business.goods.biz.dal.dao.GoodsLoanDAO;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDO;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsLoanDO;
import study.daydayup.wolf.framework.layer.domain.Repository;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * study.daydayup.wolf.business.goods.biz.repository
 *
 * @author Wingle
 * @since 2019/12/12 9:59 上午
 **/
@Component
public class LoanGoodsRepository implements  Repository {
    @Resource
    private GoodsDAO goodsDAO;
    @Resource
    private GoodsLoanDAO loanDAO;
    @Resource
    private LoanConverter converter;

    public long save(LoanEntity entity) {
        if (entity == null) {
            return 0;
        }
        entity.setId(null);

        long goodsId = saveGoodsDO(entity);
        entity.setId(goodsId);

        saveLoanDO(entity);
        return goodsId;
    }

    public void modify(LoanEntity entity) {
        if (entity == null || null == entity.getId()) {
            return;
        }

        modifyGoodsDO(entity);
        modifyLoanDO(entity);
    }

    public LoanEntity findById(long id, long orgId) {
        GoodsDO goodsDO = goodsDAO.selectById(id, orgId);
        return getLoanByGoodsDO(goodsDO);
    }

    public LoanEntity findOneByOrgId(long orgId, Integer goodsType) {
        GoodsDO goodsDO = goodsDAO.selectOneByOrgId(orgId, goodsType);
        return getLoanByGoodsDO(goodsDO);
    }

    public Page<LoanEntity> findByOrgId(long orgId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<GoodsDO> goodsDOList = goodsDAO.selectByOrgId(orgId);
        if (null == goodsDOList || goodsDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        List<LoanEntity> entityList = getLoanByGoodsDOList(goodsDOList, orgId);

        return Page.of(goodsDOList).to(entityList);
    }

    private LoanEntity getLoanByGoodsDO(GoodsDO goodsDO) {
        if (null == goodsDO) {
            return null;
        }

        LoanEntity entity = converter.toEntity(goodsDO);
        GoodsLoanDO loanDO = loanDAO.selectByGoodsId(goodsDO.getId(), goodsDO.getOrgId());
        return converter.toEntity(loanDO, entity);
    }

    private List<LoanEntity> getLoanByGoodsDOList(List<GoodsDO> goodsDOList, Long orgId) {
        List<LoanEntity> entityList = new ArrayList<>();
        Map<Long, GoodsLoanDO> loanMap = findLoanByGoodsDOList(goodsDOList, orgId);
        for (GoodsDO goodsDO : goodsDOList ) {
            LoanEntity entity = mergeGoodsAndLoan(goodsDO, loanMap.get(goodsDO.getId()));
            if (entity != null) {
                entityList.add(entity);
            }
        }

        return entityList;
    }

    private LoanEntity mergeGoodsAndLoan(GoodsDO goodsDO, GoodsLoanDO loanDO) {
        LoanEntity entity = converter.toEntity(goodsDO);
        if (goodsDO == null) {
            return entity;
        }

        return converter.toEntity(loanDO, entity);
    }

    private Map<Long, GoodsLoanDO> findLoanByGoodsDOList(List<GoodsDO> goodsDOList, long orgId) {
        List<Long> goodsIds = goodsDOList.stream()
                .map(GoodsDO::getId)
                .collect(Collectors.toList());

        List<GoodsLoanDO> loanDOList = loanDAO.selectByGoodsIdIn(goodsIds, orgId);

        Map<Long, GoodsLoanDO> loanMap = new HashMap<>();
        if (loanDOList != null) {
            loanMap = loanDOList.stream().collect(
                    Collectors.toMap(GoodsLoanDO::getGoodsId, Function.identity())
            );
        }

        return loanMap;
    }

    private long saveGoodsDO(@NonNull LoanEntity entity) {
        GoodsDO goodsDO = converter.toDo(entity);

        goodsDAO.insertSelective(goodsDO);
        Long id = goodsDO.getId();
        if (id == null) {
            throw new FailedCreateLoanException();
        }

        return id;
    }

    private void modifyGoodsDO(LoanEntity entity) {
        GoodsDO goodsDO = converter.toDo(entity);
        goodsDAO.updateByIdSelective(goodsDO);
    }

    private void saveLoanDO(LoanEntity entity) {
        Loan loan = entity.getLoan();
        if (loan == null) {
            return;
        }

        loan.setGoodsId(entity.getId());
        loan.setOrgId(entity.getOrgId());

        GoodsLoanDO loanDO = converter.toDo(loan, entity.getInstallmentList());
        loanDAO.insertSelective(loanDO);
    }

    private void modifyLoanDO(LoanEntity entity) {
        Loan loan = entity.getLoan();
        loan.setGoodsId(entity.getId());
        loan.setOrgId(entity.getOrgId());

        GoodsLoanDO loanDO = converter.toDo(loan, entity.getInstallmentList());
        loanDAO.updateByGoodsIdSelective(loanDO);
    }

}
