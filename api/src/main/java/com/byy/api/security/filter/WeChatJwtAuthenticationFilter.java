package com.byy.api.security.filter;

import com.byy.api.security.wechat.WeChatApiService;
import com.byy.api.security.wechat.WeChatDecipherData;
import com.byy.api.security.wechat.WeChatSessionData;
import com.byy.api.service.form.wechat.WeChatUserForm;
import com.byy.biz.service.wechat.WeChatUserService;
import com.byy.dal.common.utils.helper.JsonHelper;
import com.byy.dal.common.utils.helper.Transformer;
import com.byy.dal.entity.beans.security.SecurityProperties;
import com.byy.dal.entity.beans.wechat.WeChatUser;
import com.byy.dal.enums.AuthType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: yyc
 * @date: 19-3-31 下午2:31
 */
public class WeChatJwtAuthenticationFilter extends JwtTokenBasedAuthenticationFilter {

  private final WeChatApiService weChatApiService;
  private final WeChatUserService weChatUserService;

  public WeChatJwtAuthenticationFilter(
      AuthenticationManager authenticationManager,
      SecurityProperties securityProperties,
      WeChatApiService weChatApiService,
      WeChatUserService weChatUserService) {
    super(authenticationManager, securityProperties);
    this.weChatApiService = weChatApiService;
    this.weChatUserService = weChatUserService;
  }

  @Override
  protected Authentication obtainAuthToken(HttpServletRequest request) {
    WeChatUserForm form = JsonHelper.str2Obj(obtainJsonParams(request), WeChatUserForm.class);
    WeChatSessionData session = weChatApiService.genWeChatSessionByCode(form.getCode());
    String sessionKey = session.getSessionKey();
    WeChatDecipherData decipher =
        weChatApiService.decrypt(
            form.getEncryptedData(), sessionKey, form.getIv(), WeChatDecipherData.class);
    WeChatUser weChatUser = Transformer.fromBean(decipher, WeChatUser.class);
    weChatUser.setSessionKey(sessionKey);
    weChatUser = weChatUserService.saveOrUpdateWeChatUser(weChatUser, DEFAULT_PWD);
    return new UsernamePasswordAuthenticationToken(weChatUser.getId(), DEFAULT_PWD);
  }

  @Override
  protected AuthType authScope() {
    return AuthType.WECHAT;
  }
}
