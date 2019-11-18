package study.daydayup.wolf.demo.account.biz.authorization.entity.licensor;


import study.daydayup.wolf.demo.account.api.dto.LicenseDTO;
import study.daydayup.wolf.demo.account.api.dto.request.license.LicenseRequest;
import study.daydayup.wolf.demo.account.biz.authorization.entity.Authentication;
import study.daydayup.wolf.demo.account.biz.authorization.vo.ClientVO;

public interface Licensor<T extends LicenseRequest> {

    <R extends LicenseDTO> R grant(ClientVO clientVO, Authentication authentication);

    LicenseDTO authenticate(T licenseRequest);
}
