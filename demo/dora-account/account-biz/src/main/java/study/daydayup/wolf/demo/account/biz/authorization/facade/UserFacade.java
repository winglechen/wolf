package study.daydayup.wolf.demo.account.biz.authorization.facade;

import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountWechatVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatMpUserInfoResponseVO;
import com.meizizi.doraemon.user.api.dto.UserDetailDTO;
import com.meizizi.doraemon.user.api.model.UserAddress;
import com.meizizi.doraemon.user.api.model.UserProfile;
import com.meizizi.doraemon.user.api.service.UserDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserFacade {

    @Resource
    private UserDetailService userDetailService;

    public Boolean saveUserDetail(AccountWechatVO accountWechat, WechatMpUserInfoResponseVO wechatMpUserInfoResponse) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        UserProfile userProfile = new UserProfile();
        userProfile.setUid(accountWechat.getUid());
        userProfile.setMobile("");
        userProfile.setNickname(wechatMpUserInfoResponse.getNickname());
        userProfile.setGender(wechatMpUserInfoResponse.getGender());
        userProfile.setAvatar(wechatMpUserInfoResponse.getAvatar());
        userDetailDTO.setProfile(userProfile);

        userDetailDTO.setExtraInfo(null);

        UserAddress userAddress = new UserAddress();
        userAddress.setUid(accountWechat.getUid());
        userAddress.setCountry(wechatMpUserInfoResponse.getCountry());
        userAddress.setProvince(wechatMpUserInfoResponse.getProvince());
        userAddress.setCity(wechatMpUserInfoResponse.getCity());
        userDetailDTO.setUserAddress(userAddress);
        return userDetailService.save(userDetailDTO);
    }




}
