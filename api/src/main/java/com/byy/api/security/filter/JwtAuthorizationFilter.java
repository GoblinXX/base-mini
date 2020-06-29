package com.byy.api.security.filter;

import com.byy.api.security.check.TokenCheckService;
import com.byy.dal.entity.beans.security.Jwt;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录主入口
 *
 * @author: yyc
 * @date: 19-6-10 上午9:51
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private final Jwt jwt;
  private final TokenCheckService tokenCheckService;

  public JwtAuthorizationFilter(Jwt jwt, TokenCheckService tokenCheckService) {
    this.jwt = jwt;
    this.tokenCheckService = tokenCheckService;
  }

  /**
   * 在该方法中校验token是否正确, 正确则放行, 否则拦截
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param chain FilterChain
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    tokenCheckService.checkLogin(request, jwt.getHeader());
    chain.doFilter(request, response);
  }
}
