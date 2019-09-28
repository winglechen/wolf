package study.daydayup.wolf.business.account.api.service;

import study.daydayup.wolf.business.account.api.model.Orgnization;

/**
 * study.daydayup.wolf.business.account.api.service
 *
 * @author Wingle
 * @since 2019/9/27 5:47 PM
 **/
public interface OrgnizationService {
     Orgnization create();
     Orgnization find(long orgId);
     Boolean remove(long orgId);
     Boolean modify(Orgnization org);
}
