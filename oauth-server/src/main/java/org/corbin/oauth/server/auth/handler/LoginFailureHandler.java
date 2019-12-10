package org.corbin.oauth.server.auth.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-10
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
    System.out.println("认证失败");
  }
}
