package org.corbin.oauth.server.auth.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.corbin.oauth.server.auth.model.token.bean.AccessTokenPayload;
import org.corbin.oauth.server.auth.model.token.bean.JwtHeader;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置用户第一次使用用户名密码登陆成功后执行的内容<br>
 * </> 设置重定向页面,设置token之类的
 *
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-6
 */
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    System.out.println(JSON.toJSONString(authentication));

    // 设置token

    //    AccessTokenPayload payload = new AccessTokenPayload();
    //    payload.setAccountId("1");
    //    payload.setExpirationTime(new Date(System.currentTimeMillis() + 9999999999L));
    //    payload.String token = JwtSupport.buildToken(new JwtHeader(), payload);
    //
    //    response.setHeader("Authorization", token);
    //    response.getOutputStream().write(token.getBytes(Charset.defaultCharset()));
  }
}
