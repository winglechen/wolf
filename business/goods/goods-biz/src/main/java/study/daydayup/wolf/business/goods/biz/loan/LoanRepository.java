package study.daydayup.wolf.business.goods.biz.loan;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.exception.FailedCreateLoanException;
import study.daydayup.wolf.business.goods.api.exception.GoodsNotFoundException;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.goods.biz.dal.dao.GoodsDAO;
import study.daydayup.wolf.business.goods.biz.dal.dao.GoodsLoanDAO;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDO;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsLoanDO;
import study.daydayup.wolf.framework.layer.domain.Repository;

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
public class LoanRepository extends Repository {
    @Resource
    private GoodsDAO goodsDAO;
    @Resource
    private GoodsLoanDAO loanDAO;

    public long save(LoanEntity entity) {
        if (entity == null) {
            return 0;
        }
        entity.setId(0);

        long goodsId = saveGoodsDO(entity);
        saveLoanDO(goodsId, entity);
        saveInstallmentDO(goodsId, entity);

        return goodsId;
    }

    public int modify(LoanEntity entity) {
        if (entity == null) {
            return 0;
        }

        modifyGoodsDO(entity);
        modifyLoanDO(entity);
        modifyInstallmentDO(entity);

        return 1;
    }

    public LoanEntity findById(long id, long orgId) {
        GoodsDO goodsDO = goodsDAO.selectById(id, orgId);

        return getLoanByGoodsDO(goodsDO);
    }

    public LoanEntity findOneByOrgId(long orgId) {
        GoodsDO goodsDO = goodsDAO.selectOneByOrgId(orgId);

        return getLoanByGoodsDO(goodsDO);
    }

    public List<LoanEntity> findByOrgId(long orgId) {
        List<GoodsDO> goodsDOList = goodsDAO.selectByOrgId(orgId);
        if (goodsDOList.isEmpty()) {
            return null;
        }

        Map<Long, GoodsLoanDO> loanMap = findLoanByGoodsDOList(goodsDOList, orgId);
        List<LoanEntity> entityList = new ArrayList<>();

        for (GoodsDO goodsDO : goodsDOList ) {
            LoanEntity entity = mergeGoodsAndLoan(goodsDO, loanMap.get(goodsDO.getId()));
            if (entity != null) {
                entityList.add(entity);
            }
        }

        return entityList;
    }

    // private methods stat
    private LoanEntity getLoanByGoodsDO(GoodsDO goodsDO) {
        if (null == goodsDO) {
            return null;
        }

        LoanEntity entity = new LoanEntity();
        BeanUtils.copyProperties(goodsDO, entity);

        GoodsLoanDO loanDO = loanDAO.selectByGoodsId(goodsDO.getId(), goodsDO.getOrgId());
        return setLoanToEntity(entity, loanDO);
    }

    private LoanEntity mergeGoodsAndLoan(GoodsDO goodsDO, GoodsLoanDO loanDO) {
        if (goodsDO == null) {
            return null;
        }

        LoanEntity entity = new LoanEntity();
        BeanUtils.copyProperties(goodsDO, entity);
        return setLoanToEntity(entity, loanDO);
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

    private LoanEntity setLoanToEntity(LoanEntity entity, GoodsLoanDO loanDO) {
        if (loanDO == null) {
            return entity;
        }

        Loan loan = new Loan();
        BeanUtils.copyProperties(loanDO, loan);
        entity.setLoan(loan);

        List<Installment> installmentList = JSON.parseArray(loanDO.getInstallment(), Installment.class);
        entity.setInstallmentList(installmentList);

        return entity;
    }

    private long saveGoodsDO(LoanEntity entity) {
        GoodsDO goodsDO = new GoodsDO();
        BeanUtils.copyProperties(entity, goodsDO);

        Long id = goodsDAO.insertSelective(goodsDO);
        if (id == null) {
            throw new FailedCreateLoanException();
        }

        return (long)id;
    }

    private int modifyGoodsDO(LoanEntity entity) {
        long id = entity.getId();
        if (id <= 0) {
            throw new GoodsNotFoundException();
        }

        GoodsDO goodsDO = new GoodsDO();
        BeanUtils.copyProperties(entity, goodsDO);
        return goodsDAO.updateByIdSelective(goodsDO);
    }

    private void saveLoanDO(long goodsId, LoanEntity entity) {
        GoodsLoanDO loanDO = new GoodsLoanDO();
        BeanUtils.copyProperties(entity.getLoan(), loanDO);

        loanDO.setId(null);
        loanDO.setGoodsId(goodsId);
        loanDO.setOrgId(entity.getOrgId());

        String installments = JSON.toJSONString(entity.getInstallmentList());
        loanDO.setInstallment(installments);

        loanDAO.insertSelective(loanDO);
    }

    private int modifyLoanDO(LoanEntity entity) {
        Loan loan = entity.getLoan();

        GoodsLoanDO loanDO = new GoodsLoanDO();
        BeanUtils.copyProperties(loan, loanDO);

        loanDO.setId(null);
        loanDO.setOrgId(entity.getOrgId());
        loanDO.setGoodsId(entity.getId());

        String installments = JSON.toJSONString(entity.getInstallmentList());
        loanDO.setInstallment(installments);

        return loanDAO.updateByGoodsIdSelective(loanDO);
    }

    private void saveInstallmentDO(long goodsId, LoanEntity entity) {

    }

    private int modifyInstallmentDO(LoanEntity entity) {
        return 1;
    }

}
