package com.byy.api.security.filter;

import com.byy.api.service.form.phone.PhoneForm;
import com.byy.biz.service.wechat.WeChatUserService;
import com.byy.dal.common.errors.UserAuthException;
import com.byy.dal.common.utils.helper.CheckHelper;
import com.byy.dal.common.utils.helper.JsonHelper;
import com.byy.dal.common.utils.provider.WrapperProvider;
import com.byy.dal.entity.beans.security.SecurityProperties;
import com.byy.dal.entity.beans.wechat.WeChatUser;
import com.byy.dal.enums.AuthType;
import com.byy.dal.enums.GlobalErrorCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Goblin
 * @date: 19-06-26 上午11:31
 */
public class PhoneUserJwtAuthenticationFilter extends JwtTokenBasedAuthenticationFilter {

  private final WeChatUserService weChatUserService;

  public PhoneUserJwtAuthenticationFilter(
      AuthenticationManager authenticationManager,
      SecurityProperties securityProperties,
      WeChatUserService weChatUserService) {
    super(authenticationManager, securityProperties);
    this.weChatUserService = weChatUserService;
  }

  @Override
  protected Authentication obtainAuthToken(HttpServletRequest request) {
    PhoneForm phoneForm = JsonHelper.str2Obj(obtainJsonParams(request), PhoneForm.class);
    WeChatUser user =
        weChatUserService.getOne(
            WrapperProvider.oneQueryWrapper(WeChatUser::getPhone, phoneForm.getPhone()));
    CheckHelper.trueOrThrow(user != null, UserAuthException::new, GlobalErrorCode.USER_NOT_FOUND);
    return new UsernamePasswordAuthenticationToken(user.getId(), phoneForm.getPassword());
  }

  @Override
  protected AuthType authScope() {
    return AuthType.PHONE;
  }
}
