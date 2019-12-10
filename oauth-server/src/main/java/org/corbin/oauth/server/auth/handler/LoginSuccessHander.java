package org.corbin.oauth.server.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-6
 */
@Slf4j
public class LoginSuccessHander implements AuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    System.out.println("登录成功");
    response.getOutputStream().write("登录成功".getBytes());
  }
}
