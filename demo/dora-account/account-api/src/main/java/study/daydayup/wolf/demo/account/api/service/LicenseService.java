package study.daydayup.wolf.demo.account.api.service;

import study.daydayup.wolf.demo.account.api.dto.LicenseDTO;
import study.daydayup.wolf.demo.account.api.dto.request.license.OAuth2LicenseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient("account")
@RequestMapping("license")
public interface LicenseService {

    @RequestMapping(method = RequestMethod.POST, value = "/oauth2Authenticate")
    LicenseDTO oauth2Authenticate(@RequestBody @Valid OAuth2LicenseRequest licenseRequest);
}
