package com.byy.api.security.wechat;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.byy.biz.service.wechat.WeChatConfigService;
import com.byy.dal.common.errors.BizException;
import com.byy.dal.common.errors.UserAuthException;
import com.byy.dal.common.utils.helper.AesCbcHelper;
import com.byy.dal.common.utils.helper.CheckHelper;
import com.byy.dal.common.utils.helper.MapHelper;
import com.byy.dal.entity.beans.wechat.WeChatConfig;
import com.byy.dal.enums.GlobalErrorCode;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

import static com.byy.api.security.wechat.WeChatAPI.WX_QR_CODE_API;
import static com.byy.dal.common.utils.helper.JsonHelper.str2Obj;

public class WeChatApiService {

  private final RestTemplate restTemplate;
  private final WeChatConfigService weChatConfigService;

  public WeChatApiService(RestTemplate restTemplate, WeChatConfigService weChatConfigService) {
    this.restTemplate = restTemplate;
    this.weChatConfigService = weChatConfigService;
  }

  /**
   * 通过code获取sessionKey和openId
   *
   * @param code String
   * @return WeChatSessionData
   */
  public WeChatSessionData genWeChatSessionByCode(String code) {
    WeChatConfig config = obtainWeXinConfig();
    String url =
        String.format(
            WeChatAPI.JS_CODE2_SESSION_API, config.getAppId(), config.getAppSecret(), code);
    String retJson = restTemplate.getForObject(url, String.class);
    WeChatSessionData weChatSessionData = str2Obj(retJson, WeChatSessionData.class);
    boolean ret =
        weChatSessionData != null && Strings.isNotBlank(weChatSessionData.getSessionKey());
    CheckHelper.trueOrThrow(ret, UserAuthException::new, "获取微信用户信息失败");
    return weChatSessionData;
  }

  /**
   * 获取微信miniCode字节数组
   *
   * @param page String
   * @param scene T
   * @param width Integer
   * @param <T> T
   * @return byte[]
   */
  public <T> byte[] genWeChatMiniCode(String page, T scene, Integer width) {
    String sendUrl = String.format(WX_QR_CODE_API, genAccessToken());
    Map<String, Object> params = MapHelper.of("page", page, "scene", scene, "width", width);
    HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, null);
    return restTemplate.postForObject(sendUrl, httpEntity, byte[].class);
  }

  /**
   * 获取微信access_token
   *
   * @return String
   */
  private String genAccessToken() {
    WeChatConfig config = obtainWeXinConfig();
    LocalDateTime expiredAt = config.getExpiredAt();
    if (expiredAt != null && expiredAt.isAfter(LocalDateTime.now())) {
      return config.getAccessToken();
    }
    String sendUrl =
        String.format(WeChatAPI.ACCESS_TOKEN_API, config.getAppId(), config.getAppSecret());
    String retJson = restTemplate.getForObject(sendUrl, String.class);
    WeChatAccessTokenData tokenData = str2Obj(retJson, WeChatAccessTokenData.class);
    config.setAccessToken(tokenData.getAccessToken());
    config.setExpiredAt(tokenData.getExpiredAt());
    CheckHelper.trueOrThrow(
        weChatConfigService.updateById(config),
        BizException::new,
        GlobalErrorCode.ACCESS_TOKEN_UPDATE_ERROR);
    return config.getAccessToken();
  }

  /**
   * 获取三方配置
   *
   * @return WeChatConfig
   */
  private WeChatConfig obtainWeXinConfig() {
    return CheckHelper.nonEmptyOrThrow(
        weChatConfigService.getOne(Wrappers.emptyWrapper()),
        BizException::new,
        GlobalErrorCode.CONFIG_NOT_FOUND);
  }

  /**
   * 微信授权加密数据解析
   *
   * @param encryptedData String
   * @param sessionKey String
   * @param iv String
   * @param clazz Class
   * @return WeChatDecipherData
   */
  public <T> T decrypt(String encryptedData, String sessionKey, String iv, Class<T> clazz) {
    String ret = AesCbcHelper.decrypt(encryptedData, sessionKey, iv);
    return CheckHelper.nonEmptyOrThrow(
        str2Obj(ret, clazz), UserAuthException::new, "微信授权加密数据无法转化为对象");
  }
}
