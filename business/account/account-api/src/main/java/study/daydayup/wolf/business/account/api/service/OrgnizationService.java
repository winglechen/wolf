package study.daydayup.wolf.business.account.api.service;

import study.daydayup.wolf.business.account.api.entity.Orgnization;

/**
 * study.daydayup.wolf.business.account.api.service
 *
 * @author Wingle
 * @since 2019/9/27 5:47 PM
 **/
public interface OrgnizationService {
     long /*orgId*/ create(Orgnization org);
     Orgnization findById(long orgId);
     Orgnization findByName(String name);
     void removeById(long orgId);
     void modifyById(Orgnization org);
}
