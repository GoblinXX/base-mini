package com.byy.api.security.filter;

import com.byy.api.service.form.sys.SysUserForm;
import com.byy.biz.service.sys.SysUserService;
import com.byy.dal.common.errors.UserAuthException;
import com.byy.dal.common.utils.helper.CheckHelper;
import com.byy.dal.common.utils.helper.JsonHelper;
import com.byy.dal.common.utils.provider.WrapperProvider;
import com.byy.dal.entity.beans.security.SecurityProperties;
import com.byy.dal.entity.beans.sys.SysUser;
import com.byy.dal.enums.AuthType;
import com.byy.dal.enums.GlobalErrorCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: yyc
 * @date: 19-3-14 下午5:33
 */
public class SysUserJwtAuthenticationFilter extends JwtTokenBasedAuthenticationFilter {

  private final SysUserService sysUserService;

  public SysUserJwtAuthenticationFilter(
      AuthenticationManager authenticationManager,
      SecurityProperties securityProperties,
      SysUserService sysUserService) {
    super(authenticationManager, securityProperties);
    this.sysUserService = sysUserService;
  }

  @Override
  protected Authentication obtainAuthToken(HttpServletRequest request) {
    SysUserForm form = JsonHelper.str2Obj(obtainJsonParams(request), SysUserForm.class);
    SysUser user =
        sysUserService.getOne(
            WrapperProvider.oneQueryWrapper(SysUser::getUsername, form.getUsername()));
    CheckHelper.trueOrThrow(user != null, UserAuthException::new, GlobalErrorCode.USER_NOT_FOUND);
    return new UsernamePasswordAuthenticationToken(user.getId(), form.getPassword());
  }

  @Override
  protected AuthType authScope() {
    return AuthType.SYS;
  }
}
