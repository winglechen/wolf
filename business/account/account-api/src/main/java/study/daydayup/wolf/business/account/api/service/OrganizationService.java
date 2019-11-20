package study.daydayup.wolf.business.account.api.service;

import study.daydayup.wolf.business.account.api.entity.Organization;

/**
 * study.daydayup.wolf.business.account.api.service
 *
 * @author Wingle
 * @since 2019/9/27 5:47 PM
 **/
public interface OrganizationService {
     long /*orgId*/ create(Organization org);
     Organization findById(long orgId);
     Organization findByName(String name);
     void removeById(long orgId);
     void modifyById(Organization org);
}
