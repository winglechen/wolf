package study.daydayup.wolf.demo.account.biz.authorization.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import study.daydayup.wolf.demo.account.api.enums.AuthTypeEnum;
import study.daydayup.wolf.demo.account.api.exception.ThirdAuthorizeOauthException;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatMpOAuthResponseVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatMpUserInfoResponseVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatSessionKeyVO;
import study.daydayup.wolf.demo.account.biz.configuration.properties.WechatProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class WechatAuthorizationFacade {

    @Resource
    private WechatProperties wechatProperties;

    public WechatMpOAuthResponseVO authorize(Integer authorizationType, String authorizationCode) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String requestBase = authorizationType.equals(AuthTypeEnum.WECHAT_APP.getCode()) ? wechatProperties.getAppAccessTokenUrl() : wechatProperties.getMpAccessTokenUrl();
        String requestUrl = requestBase + authorizationCode;
        CloseableHttpResponse response = null;
        HttpGet httpget = new HttpGet(requestUrl);
        String result = null;
        try {
            response = closeableHttpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            log.warn("WechatAuthorizationFacade authorize request error; requestUrl:{}, e:{}", requestUrl, e);
            throw new ThirdAuthorizeOauthException("微信换取授权访问令牌失败");
        }
        if (result == null) {
            log.warn("WechatAccessToken authorize result empty requestUrl:{}", requestUrl);
            throw new ThirdAuthorizeOauthException("微信换取授权访问令牌失败");
        }

        JSONObject parseObject = JSON.parseObject(result);
        if (parseObject.containsKey("errcode") || !parseObject.containsKey("access_token")) {
            log.info("WechatAccessToken authorize result error requestUrl:{}, result:{}", requestUrl, result);
            throw new ThirdAuthorizeOauthException("微信换取授权访问令牌失败");
        }

        String openId = parseObject.getString("openid");
        String unionId = parseObject.getString("unionid");
        String accessToken = parseObject.getString("access_token");
        Integer expiresIn = parseObject.getInteger("expires_in");
        String refreshToken = parseObject.getString("refresh_token");
        String scope = parseObject.getString("scope");
        return new WechatMpOAuthResponseVO(openId, unionId, accessToken, expiresIn, refreshToken, scope);
    }

    public WechatMpUserInfoResponseVO getWechatUserInfo(String accessToken, String openId) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String requestUrl = wechatProperties.getUserInfoUrl() + "?access_token=" + accessToken + "&openid=" + openId;
        HttpGet httpget = new HttpGet(requestUrl);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = closeableHttpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            log.error("WechatUserInfoFacade getWechatUserInfo error requestUrl:{}, e:{}", requestUrl, e);
            throw new ThirdAuthorizeOauthException("获取微信用户信息异常");
        }
        if (result == null) {
            log.error("WechatUserInfoFacade getWechatUserInfo result empty requestUrl:{}, result:{}", requestUrl, result);
            throw new ThirdAuthorizeOauthException("获取微信用户信息异常");
        }
        JSONObject parseObject = JSON.parseObject(result);
        if (parseObject.containsKey("errcode") || !parseObject.containsKey("nickname")) {
            log.error("WechatUserInfoFacade getWechatUserInfo result error requestUrl:{}, result:{}", requestUrl, result);
            throw new ThirdAuthorizeOauthException("获取微信用户信息失败");
        }

        String openId2 = parseObject.getString("openid");
        String unionId = parseObject.getString("unionid");
        if (!openId2.equals(openId)) {
            throw new ThirdAuthorizeOauthException("微信用户信息不匹配");
        }

        String nickname = parseObject.getString("nickname");
        Integer sex = parseObject.getInteger("sex");
        String province = parseObject.getString("province");
        String city = parseObject.getString("city");
        String country = parseObject.getString("country");
        String headImgUrl = parseObject.getString("headimgurl");
        //todo
        JSONArray privilege = parseObject.getJSONArray("privilege");
        return new WechatMpUserInfoResponseVO(openId, unionId, nickname, sex, headImgUrl, country, province, city);
    }

    public WechatSessionKeyVO getSessionKey(String authorizationCode) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String requestUrl = wechatProperties.getSessionKeyUrl() + authorizationCode;
        HttpGet httpget = new HttpGet(requestUrl);
        CloseableHttpResponse response = null;
        String result = null;
        log.info("WechatAuthorizationFacade getSessionKey requestUrl:{}", requestUrl);
        try {
            response = closeableHttpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            log.info("WechatAuthorizationFacade getSessionKey requestUrl:{}, e:{}", requestUrl, e);
            throw new ThirdAuthorizeOauthException("获取小程序登录态失败");
        }
        if (result == null) {
            throw new ThirdAuthorizeOauthException("获取小程序登录态错误");
        }
        JSONObject parseObject = JSON.parseObject(result);
        if (parseObject.containsKey("errcode") || !parseObject.containsKey("session_key")) {
            log.error("WechatAuthorizationFacade getSessionKey error requestUrl:{}, result:{}", requestUrl, result);
            throw new ThirdAuthorizeOauthException("获取小程序登录态异常");
        }

        String openId = parseObject.getString("openid");
        String unionId = parseObject.getString("unionid");
        String sessionKey = parseObject.getString("session_key");
        return new WechatSessionKeyVO(sessionKey, openId, unionId);
    }









}
